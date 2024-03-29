package com.example.online_movie_ticketing_application.Services.Impl;

import com.example.online_movie_ticketing_application.Convertors.MovieConvertors;
import com.example.online_movie_ticketing_application.CustomExceptions.NoShowException;
import com.example.online_movie_ticketing_application.CustomExceptions.NoMovieException;
import com.example.online_movie_ticketing_application.Entities.*;
import com.example.online_movie_ticketing_application.ResponseDto.MovieAndItsShowsResponseDto;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.Enums.TicketStatus;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.TicketRepository;
import com.example.online_movie_ticketing_application.ResponseDto.MovieCollectionResponseDto;
import com.example.online_movie_ticketing_application.ResponseDto.ShowDateAndTimeResponseDto;
import com.example.online_movie_ticketing_application.Services.MovieService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TicketRepository ticketRepository;

    public int totalCollectionOfMovie(String movieName){
        int totalCollection = ticketRepository.getTotalCollectionOfMovie(movieName);
        if(totalCollection==0){
            throw new EntityNotFoundException();
        }
        else {
            return totalCollection;
        }
    }

    public MovieCollectionResponseDto movieWithMaxCollection() throws Exception {
        Map<String,Integer> movieAndItsCollectionMap = allMoviesTotalCollection();
        if(movieAndItsCollectionMap.isEmpty()){
            throw new Exception("No movie found");
        }
        Map.Entry<String, Integer> firstEntry = movieAndItsCollectionMap.entrySet().iterator().next();
        String movieName = firstEntry.getKey();
        int totalCollection = firstEntry.getValue();
        MovieCollectionResponseDto movieCollectionResponseDto = new MovieCollectionResponseDto();
        movieCollectionResponseDto.setMovieName(movieName);
        movieCollectionResponseDto.setTotalCollection(totalCollection);
        return movieCollectionResponseDto;
    }


    public Map<String, Integer> allMoviesTotalCollection() throws Exception {
        Map<String,Integer> movieAndItsCollection = new HashMap<>();
        List<TicketEntity> ticketEntityList = ticketRepository.findAll();
        for(TicketEntity ticketEntity : ticketEntityList){
            if(ticketEntity.getStatus().equals(TicketStatus.CANCELLED)){
                continue;
            }
            String movieName = ticketEntity.getMovieName();
            int totalAmount = ticketEntity.getTotalAmount();
            int oldCollection = movieAndItsCollection.getOrDefault(movieName,0);
            movieAndItsCollection.put(movieName,oldCollection + totalAmount);
        }

        if(movieAndItsCollection.isEmpty()){
            throw new Exception("No movie found");
        }

        return movieAndItsCollection.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e2,
                                LinkedHashMap::new)
                );
    }

    public MovieAndItsShowsResponseDto movieWithMaxShows() {

        String movieName = "";
        int numberOfShows = 0;
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        for(MovieEntity movieEntity : movieEntityList){
            int currentNumberOfShows = movieEntity.getShowEntityList().size();
            if(currentNumberOfShows>numberOfShows){
                numberOfShows = currentNumberOfShows;
                movieName = movieEntity.getMovieName();
            }
        }
        return new MovieAndItsShowsResponseDto(movieName,numberOfShows);
    }

    public List<ShowDateAndTimeResponseDto> getShowTime(String movieName, String theaterName) throws NoShowException, NoMovieException {
        List<ShowDateAndTimeResponseDto> pairList = new ArrayList<>();
        Optional<MovieEntity> optionalMovieEntity = Optional.ofNullable(movieRepository.findByMovieName(movieName));
        if(optionalMovieEntity.isPresent()){
            MovieEntity movieEntity = optionalMovieEntity.get();
            List<ShowEntity> showEntityList = movieEntity.getShowEntityList();
            for(ShowEntity showEntity : showEntityList){
                TheaterEntity theaterEntity = showEntity.getTheaterEntity();
                if(theaterEntity.getTheaterName().equals(theaterName)){
                    LocalDate showDate = showEntity.getShowDate();
                    LocalTime showTime = showEntity.getShowTime();
                    ShowDateAndTimeResponseDto showDateAndTimeResponseDto = new ShowDateAndTimeResponseDto(showDate,showTime);
                    pairList.add(showDateAndTimeResponseDto);
                }
            }
            if(pairList.isEmpty()){
                throw new NoShowException("There is no show of " + movieName + " running in " + theaterName);
            }
            else {
                return pairList;
            }
        }
        else {
            throw new NoMovieException("There is no movie named " + movieName);
        }
    }

    public List<MovieEntity> getAllMovies(){
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        if(movieEntityList.isEmpty()){
            throw new EntityNotFoundException();
        }
        else {
            return movieEntityList;
        }
    }

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
