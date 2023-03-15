package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;

public class TicketConvertor {
    public static TicketDetailsResponseDto convertEntityToDto(TicketEntity ticketEntity){
        return TicketDetailsResponseDto.builder()
                .id(ticketEntity.getId())
                .ticketId(ticketEntity.getTicketId())
                .theaterName(ticketEntity.getTheaterName())
                .totalAmount(ticketEntity.getTotalAmount())
                .movieName(ticketEntity.getMovieName())
                .showEntityId(ticketEntity.getShowEntity().getId())
                .bookedSeats(ticketEntity.getBookedSeats())
                .showDate(ticketEntity.getShowDate())
                .showTime(ticketEntity.getShowTime())
                .userEntityId(ticketEntity.getUserEntity().getId())
                .status(ticketEntity.getStatus())
                .build();
    }
}
