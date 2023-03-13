package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.Convertors.ShowConvertors;
import com.example.online_movie_ticketing_application.Entities.*;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.Enums.SeatType;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    @PersistenceContext
    EntityManager entityManager;

    public String addShow(ShowEntryDto showEntryDto){

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();

        long showExists = showRepository.countByTheaterEntityIdAndShowDateAndShowTime(showEntryDto.getTheaterId(),
                showEntryDto.getShowDate(), showEntryDto.getShowTime());
        if(showExists >= 1){
            return "Show already scheduled at given date, time and theater";
        }

        //1. converting to entity
        ShowEntity showEntity = ShowConvertors.convertEntrytoEntity(showEntryDto);


        //2. setting the attributes of foreign key
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        //Pending attributes :- List of showSeatEntity

        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDto, showEntity);

        showEntity.setShowSeatEntityList(showSeatEntityList);

        //Now we also need to update the parent entities and parents of this
        //showEntity are movieEntity and theatreEntity
        //fetching the showEntity list of the given
        //movieEntity and then after adding this showEntity also to the same list and saving the parent afterward


        List<ShowEntity> showEntityListFromMovie = movieEntity.getShowEntityList();
        showEntityListFromMovie.add(showEntity);
        movieRepository.save(movieEntity);

        List<ShowEntity> showEntityListFromTheater = theaterEntity.getShowEntityList();
        showEntityListFromTheater.add(showEntity);
        entityManager.detach(showEntity);
        entityManager.flush();
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

            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;
    }

    public String removeShow(int showId){
        ShowEntity showEntity = showRepository.findById(showId).get();
        LocalTime localTime = LocalTime.now();
        if(showEntity.getShowTime().compareTo(LocalTime.now())>0){
            return "CANCELED";
        }
        showRepository.deleteById(showId);
        return "REMOVED";
    }

}
