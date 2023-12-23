package com.example.online_movie_ticketing_application.ResponseDto;

import lombok.*;


@Data
@AllArgsConstructor
public class MovieAndItsShowsResponseDto {
    private String movieName;
    private int numberOfShows;
}
