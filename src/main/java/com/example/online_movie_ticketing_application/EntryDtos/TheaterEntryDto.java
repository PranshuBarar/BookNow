package com.example.online_movie_ticketing_application.EntryDtos;

import lombok.Data;

@Data
public class TheaterEntryDto {
    //Attributes that we require
    private String name;
    private String location;
    private int classicSeatsCount;
    private int premiumSeatsCount;
}
