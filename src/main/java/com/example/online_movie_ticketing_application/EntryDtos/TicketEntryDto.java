package com.example.online_movie_ticketing_application.EntryDtos;

import lombok.Data;

import java.util.List;

@Data
public class TicketEntryDto {
    private int showId;
    private int userId;
    private List<String> requestedSeats;
}
