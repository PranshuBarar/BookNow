package com.example.online_movie_ticketing_application.ResponseDto;

import com.example.online_movie_ticketing_application.Enums.Genre;
import com.example.online_movie_ticketing_application.Enums.Language;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
public class MovieEntityResponseDto {
    private int id;
    private String movieName;
    private double ratings;
    private int duration;
    private Language language;
    private Genre genre;
}
