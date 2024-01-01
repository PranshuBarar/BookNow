package com.example.online_movie_ticketing_application.EntryDtos;

import com.example.online_movie_ticketing_application.Enums.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDto {
    private LocalDate showDate;

    private LocalTime showTime;

    private ShowType showType;

    private String movieName;

    private String theaterName;

    private int classicSeatPrice;

    private int premiumSeatPrice;
}
