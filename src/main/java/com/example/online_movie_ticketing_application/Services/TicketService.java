package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TicketEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;

import java.util.List;

public interface TicketService {
    String bookTicket(TicketEntryDto ticketEntryDto) throws Exception;

    String cancelTicket(int ticketId);

    TicketDetailsResponseDto getDetails(String ticketUUID);

    List<TicketEntity> getTicketsBookedByUser(String userEmail);
}
