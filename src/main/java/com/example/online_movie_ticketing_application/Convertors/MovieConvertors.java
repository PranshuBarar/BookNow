package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class MovieConvertors {

    public static MovieEntity convertMovieEntryDtoToEntity(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();
        return movieEntity;
    }
}
