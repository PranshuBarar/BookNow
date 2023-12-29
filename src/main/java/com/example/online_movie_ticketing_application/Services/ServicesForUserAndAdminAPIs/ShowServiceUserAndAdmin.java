package com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Convertors.ShowConvertors;
import com.example.online_movie_ticketing_application.Entities.*;
import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.Enums.SeatType;
import com.example.online_movie_ticketing_application.Enums.ShowCancellationResponse;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceUserAndAdmin {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

}
