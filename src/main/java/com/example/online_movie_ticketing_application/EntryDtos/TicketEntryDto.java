package com.example.online_movie_ticketing_application.EntryDtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TicketEntryDto {
    private String theaterName;
    private LocalDate showDate;
    private LocalTime showTime;
    private String userName;
    private List<String> requestedSeats;

}
