package com.example.online_movie_ticketing_application.EntryDtos;


import com.example.online_movie_ticketing_application.Enums.Genre;
import com.example.online_movie_ticketing_application.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MovieEntryDto {
    @Column(unique = true, nullable = false)
    private String movieName;
    private double ratings;
    private int duration;
    private Language language;
    private Genre genre;
}
