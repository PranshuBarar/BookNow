package com.example.online_movie_ticketing_application.ResponseDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ShowDateAndTimeResponseDto {
    private LocalDate showDate;
    private LocalTime showTime;
}
