package com.example.online_movie_ticketing_application.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieCollectionResponseDto {
    String movieName;
    int totalCollection;
}
