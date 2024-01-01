package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;

public class TheaterConvertors {
    public static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto){
        return TheaterEntity.builder().location((theaterEntryDto.getLocation()))
                .theaterName(theaterEntryDto.getName()).build();
    }
}
