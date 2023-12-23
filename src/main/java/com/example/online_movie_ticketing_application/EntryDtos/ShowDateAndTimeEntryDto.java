package com.example.online_movie_ticketing_application.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShowDateAndTimeEntryDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private String theaterName;
}
