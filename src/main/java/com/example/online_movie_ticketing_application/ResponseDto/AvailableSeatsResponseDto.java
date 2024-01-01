package com.example.online_movie_ticketing_application.ResponseDto;

import com.example.online_movie_ticketing_application.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AvailableSeatsResponseDto {
    String seatNo;
    SeatType seatType;
    int price;
}
