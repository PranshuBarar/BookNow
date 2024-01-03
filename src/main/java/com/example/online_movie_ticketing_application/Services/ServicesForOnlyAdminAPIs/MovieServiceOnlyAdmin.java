package com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs;

import com.example.online_movie_ticketing_application.Convertors.MovieConvertors;
import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.TicketRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieServiceOnlyAdmin {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String addMovie(MovieEntryDto movieEntryDto){
        boolean movieExists = movieRepository.existsByMovieName(movieEntryDto.getMovieName());
        if(movieExists){
            throw new EntityExistsException("Movie already Exists");
        }
        MovieEntity movieEntity = MovieConvertors.convertMovieEntryDtoToEntity(movieEntryDto);
        movieRepository.save(movieEntity);
        return "Movie added successfully";
    }

    @Transactional
    public String removeMovie(String movieName) {
        int deletionResponse = movieRepository.deleteByMovieName(movieName);
        if(deletionResponse>0){
            return "Movie deleted successfully";
        }
        else {
            throw new EntityNotFoundException("No movie with this name");
        }
    }
}
