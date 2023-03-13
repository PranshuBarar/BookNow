package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.Convertors.MovieConvertors;
import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity = MovieConvertors.convertMovieEntryDtoToEntity(movieEntryDto);
        boolean movieExists = movieRepository.existsByMovieName(movieEntryDto.getMovieName());
        if(movieExists){
            return "Movie already exists";
        }
        movieRepository.save(movieEntity);
        return "Movie added successfully";
    }

    public String removeMovie(int movieId){
        movieRepository.deleteById(movieId);
        return "Movie deleted successfully";
    }
}
