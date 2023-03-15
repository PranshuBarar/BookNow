package com.example.online_movie_ticketing_application.ResponseDto;
import com.example.online_movie_ticketing_application.Enums.TicketStatus;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class TicketDetailsResponseDto {
    private int id;

    private String movieName; //to be printed

    private LocalDate showDate; //to be printed

    private LocalTime showTime; //to be printed

    private int totalAmount; //to be printed

    private String ticketId = UUID.randomUUID().toString(); //to be printed

    private String theaterName; //to be printed

    private String bookedSeats;

    private TicketStatus status;

    int showEntityId;

    int userEntityId;
}
