package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.Convertors.MovieConvertors;
import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.Repository.MovieRepository;
import com.example.online_movie_ticketing_application.Repository.TicketRepository;
import com.example.online_movie_ticketing_application.ResponseDto.MovieCollectionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TicketRepository ticketRepository;
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

    public int totalCollectionOfMovie(String movieName){
        return ticketRepository.getTotalCollectionOfMovie(movieName);
    }

    public MovieCollectionResponseDto movieWithMaxCollection(){
        Map<String,Integer> movieAndItsCollectionMap = allMoviesTotalCollection();
        Map.Entry<String, Integer> firstEntry = movieAndItsCollectionMap.entrySet().iterator().next();
        String movieName = firstEntry.getKey();
        Integer totalCollection = firstEntry.getValue();
        MovieCollectionResponseDto movieCollectionResponseDto = new MovieCollectionResponseDto();
        movieCollectionResponseDto.setMovieName(movieName);
        movieCollectionResponseDto.setTotalCollection(totalCollection);
        return movieCollectionResponseDto;
    }


    public Map<String, Integer> allMoviesTotalCollection() {
        Map<String,Integer> movieAndItsCollectionMap = new HashMap<>();
        List<TicketEntity> ticketEntityList = ticketRepository.findAll();
        for(TicketEntity ticketEntity : ticketEntityList){
            String movieName = ticketEntity.getMovieName();
            int totalAmount = ticketEntity.getTotalAmount();
            int oldCollection = movieAndItsCollectionMap.getOrDefault(movieName,0);
            movieAndItsCollectionMap.put(movieName,oldCollection + totalAmount);
        }

        Map<String, Integer> sortedMap = movieAndItsCollectionMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new)
                );

        return movieAndItsCollectionMap;
    }

    public Pair<Integer,String> movieWithMaxShows() {

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

        assert false;
        return Pair.of(numberOfShows,movieName);
    }

    public List<Pair<LocalDate, LocalTime>> getShowTime(int movieId, int theaterId) {
        List<Pair<LocalDate, LocalTime>> pairList = new ArrayList<>();
        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();
        for(ShowEntity showEntity : showEntityList){
            TheaterEntity theaterEntity = showEntity.getTheaterEntity();
            if(theaterEntity.getId() == theaterId){
                LocalDate showDate = showEntity.getShowDate();
                LocalTime showTime = showEntity.getShowTime();
                Pair<LocalDate,LocalTime> pair = Pair.of(showDate,showTime);
                pairList.add(pair);
            }
        }
        return pairList;
    }
}
