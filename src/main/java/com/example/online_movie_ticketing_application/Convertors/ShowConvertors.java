package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.Entities.ShowSeatEntity;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.AvailableSeatsResponseDto;

public class ShowConvertors {
    public static ShowEntity convertEntrytoEntity(ShowEntryDto showEntryDto){

        return ShowEntity.builder()
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType()).build();
    }

    public static AvailableSeatsResponseDto availableSeatsResponseDtoConvertor(ShowSeatEntity showSeatEntity){
        return AvailableSeatsResponseDto
                .builder()
                .seatNo(showSeatEntity.getSeatNo())
                .seatType(showSeatEntity.getSeatType())
                .price(showSeatEntity.getPrice())
                .build();
    }

}
