package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;

import java.util.Map;

public interface TheaterService {
    Map<String, String> theaterWithUniqueLocations();

    String addTheater(TheaterEntryDto theaterEntryDto) throws Exception;

    String removeTheater(String theaterName) throws Exception;
}
