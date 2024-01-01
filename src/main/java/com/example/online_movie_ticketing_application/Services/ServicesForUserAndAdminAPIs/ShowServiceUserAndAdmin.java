package com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Convertors.ShowConvertors;
import com.example.online_movie_ticketing_application.Entities.*;
import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import com.example.online_movie_ticketing_application.ResponseDto.AvailableSeatsResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceUserAndAdmin {
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

        ShowEntity showEntity = showRepository.findByTheaterIdAndShowDateAndShowTime(showDate, showTime, theaterId);
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
}
