package com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Convertors.ShowConvertors;
import com.example.online_movie_ticketing_application.CustomExceptions.ShowAlreadyExistsException;
import com.example.online_movie_ticketing_application.Entities.*;
import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.Enums.Role;
import com.example.online_movie_ticketing_application.Enums.SeatType;
import com.example.online_movie_ticketing_application.Enums.ShowCancellationResponse;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import com.example.online_movie_ticketing_application.ResponseDto.AvailableSeatsResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public List<AvailableSeatsResponseDto> getAvailableSeats(ShowDateAndTimeEntryDto showDateAndTimeEntryDto) {
        LocalDate showDate = showDateAndTimeEntryDto.getShowDate();
        LocalTime showTime = showDateAndTimeEntryDto.getShowTime();
        String theaterName = showDateAndTimeEntryDto.getTheaterName();

        TheaterEntity theaterEntity = theaterRepository.findByTheaterName(theaterName);
        if(theaterEntity == null){
            throw new EntityNotFoundException("Theater Not found");
        }
        int theaterId = theaterEntity.getId();

        ShowEntity showEntity = showRepository.findByTheaterEntityIdAndShowDateAndShowTime(showDate, showTime, theaterId);
        if(showEntity == null){
            throw new EntityNotFoundException("Show not found");
        }

        return currentlyAvailableSeats(showEntity);
    }


    private List<AvailableSeatsResponseDto> currentlyAvailableSeats(ShowEntity showEntity) {
        return showEntity
                .getShowSeatEntityList()
                .stream()
                .filter(showSeatEntity -> !showSeatEntity.isBooked())
                .map(ShowConvertors::availableSeatsResponseDtoConvertor)
                .toList();
    }


    public String addShow(ShowEntryDto showEntryDto) throws ShowAlreadyExistsException {


        String movieName = showEntryDto.getMovieName();
        String theaterName = showEntryDto.getTheaterName();

        MovieEntity movieEntity = movieRepository.findByMovieName(movieName);
        TheaterEntity theaterEntity = theaterRepository.findByTheaterName(theaterName);

        boolean showExists = showRepository.existsByTheaterEntityIdAndShowDateAndShowTime(movieEntity.getId(),
                showEntryDto.getShowDate(), showEntryDto.getShowTime());
        if(showExists){
            throw new ShowAlreadyExistsException();
        }

        //1. converting to entity
        ShowEntity showEntity = ShowConvertors.convertEntrytoEntity(showEntryDto);

        //2. setting the attributes of foreign key
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        //==================================================
        //Attributes which are still pending :- List of showSeatEntity

        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDto, showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);


        /*
        => Now we also need to update the parent entities
        => Parents of this showEntity are movieEntity and theatreEntity
        => Fetching the showEntity list of the given movieEntity and then after adding this showEntity also to the same list and saving the parent afterward
        */

        ShowEntity updatedShowEntity = showRepository.save(showEntity);
        List<ShowEntity> showEntityListFromMovie = movieEntity.getShowEntityList();
        showEntityListFromMovie.add(updatedShowEntity);
        movieRepository.save(movieEntity);

        List<ShowEntity> showEntityListFromTheater = theaterEntity.getShowEntityList();
        showEntityListFromTheater.add(updatedShowEntity);
        theaterRepository.save(theaterEntity);
        return "The show has been added successfully";

    }

    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto, ShowEntity showEntity) {

        /*Now the goal is to create the showSeatEntity
         * and for that we need to set all the attributes of showSeatEntity
         *
         * Following are the attributes of showSeatEntity :-
         * isBooked
         * price
         * seatNo
         * seatType
         * bookedAt
         * showEntity
         *
         * */

        TheaterEntity theaterEntity = showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();
        for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList){
            ShowSeatEntity showSeatEntity = getShowSeatEntity(showEntryDto, showEntity, theaterSeatEntity);
            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;
    }

    private static ShowSeatEntity getShowSeatEntity(ShowEntryDto showEntryDto, ShowEntity showEntity, TheaterSeatEntity theaterSeatEntity) {
        ShowSeatEntity showSeatEntity = new ShowSeatEntity();
        showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
        showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

        if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
            showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
        }
        else {
            showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
        }

        showSeatEntity.setBooked(false);
        showSeatEntity.setShowEntity(showEntity); //Setting foreign key
        return showSeatEntity;
    }

    public ShowCancellationResponse cancelShow(ShowDateAndTimeEntryDto showDateAndTimeEntryDto) throws Exception {
        String theaterName = showDateAndTimeEntryDto.getTheaterName();
        LocalDate showDate = showDateAndTimeEntryDto.getShowDate();
        LocalTime showTime = showDateAndTimeEntryDto.getShowTime();
        try{
            TheaterEntity theaterEntity = theaterRepository.findByTheaterName(theaterName);
            List<ShowEntity> showEntityList = theaterEntity.getShowEntityList();
            for(ShowEntity showEntity : showEntityList){
                if(showEntity.getShowDate() == showDate && showEntity.getShowTime() == showTime){
                    if(LocalDate.now().isBefore(showDate) && LocalTime.now().isBefore(showTime)){
                        showRepository.deleteById(showEntity.getId());
                        return ShowCancellationResponse.SHOW_IS_CANCELED_SUCCESSFULLY;
                    }
                    else {
                        return ShowCancellationResponse.SHOW_IS_ALREADY_OVER;
                    }
                }
            }
            return ShowCancellationResponse.SHOW_NOT_FOUND;
        } catch(Exception e){
            throw new Exception("Theater not found");
        }
    }
}
