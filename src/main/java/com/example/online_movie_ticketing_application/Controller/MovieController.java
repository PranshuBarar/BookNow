package com.example.online_movie_ticketing_application.Controller;
import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.MovieAndItsShowsResponseDto;
import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.ResponseDto.MovieCollectionResponseDto;
import com.example.online_movie_ticketing_application.ResponseDto.ShowDateAndTimeResponseDto;
import com.example.online_movie_ticketing_application.Services.Impl.MovieServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
@SecurityRequirement(name = "Bearer Authentication")
public class MovieController {

    @Autowired
    MovieServiceImpl movieServiceImpl;


    @GetMapping("/test") //http://localhost:8080/movies/test
    public ResponseEntity<String> test(){
        String response = "hello how are you?";
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get-show-time") //http://localhost:8080/movies/get-show-time?movie-name=<name_here>&theater-name=<name_here>
    public ResponseEntity<?> getShowTime(@RequestParam("movie-name") String movieName,
                                                           @RequestParam("theater-name") String theaterName){
        try{
            List<ShowDateAndTimeResponseDto> pairList = movieServiceImpl.getShowTime(movieName,theaterName);
            return new ResponseEntity<>(pairList,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/movie-with-max-shows") //http://localhost:8080/movies/movie-with-max-shows
    public ResponseEntity<?> movieWithMaxShows(){
        try{
            MovieAndItsShowsResponseDto movieAndItsShowsResponseDto = movieServiceImpl.movieWithMaxShows();
            return new ResponseEntity<>(movieAndItsShowsResponseDto,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>("No movie found", HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/movie-with-max-collection") //http://localhost:8080/movies/movie-with-max-collection
    public ResponseEntity<?> movieWithMaxCollection() {
        try{
            MovieCollectionResponseDto movieCollectionResponseDto = movieServiceImpl.movieWithMaxCollection();
            return new ResponseEntity<>(movieCollectionResponseDto,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>("No movie found", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all-movies-total-collection") //http://localhost:8080/movies/all-movies-total-collection
    public ResponseEntity<?> allMoviesTotalCollection() {
        try{
            Map<String,Integer> moviesAndTheirCollections = movieServiceImpl.allMoviesTotalCollection();
            return new ResponseEntity<>(moviesAndTheirCollections,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/collection/{movie}") //http://localhost:8080/movies/collection/<movie>
    public ResponseEntity<?> totalCollectionOfMovie(@PathVariable("movie") String movieName){
        try{
            int collection = movieServiceImpl.totalCollectionOfMovie(movieName);
            return new ResponseEntity<>(collection,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>("No movie with movieName: "+movieName, HttpStatus.NOT_FOUND );
        }

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/allmovies") //http://localhost:8080/movies/allmovies
    public ResponseEntity<?> getAllMovies(){
        try{
            List<MovieEntity> movieEntityList = movieServiceImpl.getAllMovies();
            return new ResponseEntity<>(movieEntityList,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>("No movies found", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping("/add") //http://localhost:8080/movies/add
    public ResponseEntity<?> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try{
            String result = movieServiceImpl.addMovie(movieEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /*
        Copy-paste the following in postman
        {
           "movieName" : "Inception",
           "ratings" : 7.6,
           "duration" : 3,
           "language" : "ENGLISH",
           "genre" : "ACTION"
        }
    */

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @DeleteMapping("/remove") //http://localhost:8080/movies/remove?movieName=<name_here>
    public ResponseEntity<String> removeMovie(@RequestParam("movieName") String movieName) {
        try{
            String response = movieServiceImpl.removeMovie(movieName);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>("Movie with this name: \"" + movieName + "\" does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
