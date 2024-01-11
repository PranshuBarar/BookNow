package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.CustomExceptions.NoShowException;
import com.example.online_movie_ticketing_application.CustomExceptions.NoMovieException;
import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.MovieAndItsShowsResponseDto;
import com.example.online_movie_ticketing_application.ResponseDto.MovieCollectionResponseDto;
import com.example.online_movie_ticketing_application.ResponseDto.ShowDateAndTimeResponseDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface MovieService {
    int totalCollectionOfMovie(String movieName);

    MovieCollectionResponseDto movieWithMaxCollection() throws Exception;

    Map<String, Integer> allMoviesTotalCollection() throws Exception;

    MovieAndItsShowsResponseDto movieWithMaxShows();

    List<ShowDateAndTimeResponseDto> getShowTime(String movieName, String theaterName) throws NoShowException, NoMovieException;

    List<MovieEntity> getAllMovies();

    String addMovie(MovieEntryDto movieEntryDto) throws EntityExistsException;

    @Transactional
    String removeMovie(String movieName) throws EntityNotFoundException;
}
