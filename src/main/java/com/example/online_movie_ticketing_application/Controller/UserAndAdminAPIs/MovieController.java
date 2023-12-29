package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;
import com.example.online_movie_ticketing_application.ResponseDto.MovieAndItsShowsResponseDto;
import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import com.example.online_movie_ticketing_application.ResponseDto.MovieCollectionResponseDto;
import com.example.online_movie_ticketing_application.ResponseDto.ShowDateAndTimeResponseDto;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.MovieServiceUserAndAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieServiceUserAndAdmin movieServiceUserAndAdmin;

    @GetMapping("/test") //http://localhost:8080/movies/test
    public ResponseEntity<String> test(){
        String response = "hello how are you test?";
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/hello") //http://localhost:8080/movies/hello
    public ResponseEntity<String> hello(){
        String response = "hello how are you?";
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-show-time") //http://localhost:8080/movies/get-show-time?movie-name=<name_here>&theater-name=<name_here>
    public ResponseEntity<?> getShowTime(@RequestParam("movie-name") String movieName,
                                                           @RequestParam("theater-name") String theaterName){
        try{
            List<ShowDateAndTimeResponseDto> pairList = movieServiceUserAndAdmin.getShowTime(movieName,theaterName);
            return new ResponseEntity<>(pairList,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/movie-with-max-shows") //http://localhost:8080/movies/movie-with-max-shows
    public ResponseEntity<?> movieWithMaxShows(){
        try{
            MovieAndItsShowsResponseDto movieAndItsShowsResponseDto = movieServiceUserAndAdmin.movieWithMaxShows();
            return new ResponseEntity<>(movieAndItsShowsResponseDto,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>("No movie found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/movie-with-max-collection") //http://localhost:8080/movies/movie-with-max-collection
    public ResponseEntity<?> movieWithMaxCollection() {
        try{
            MovieCollectionResponseDto movieCollectionResponseDto = movieServiceUserAndAdmin.movieWithMaxCollection();
            return new ResponseEntity<>(movieCollectionResponseDto,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>("No movie found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-movies-total-collection") //http://localhost:8080/movies/all-movies-total-collection
    public ResponseEntity<?> allMoviesTotalCollection() {
        try{
            Map<String,Integer> moviesAndTheirCollections = movieServiceUserAndAdmin.allMoviesTotalCollection();
            return new ResponseEntity<>(moviesAndTheirCollections,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/collection/{movie}") //http://localhost:8080/movies/collection/<movie>
    public ResponseEntity<?> totalCollectionOfMovie(@PathVariable("movie") String movieName){
        try{
            int collection = movieServiceUserAndAdmin.totalCollectionOfMovie(movieName);
            return new ResponseEntity<>(collection,HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>("No movie with movieName: "+movieName, HttpStatus.NOT_FOUND );
        }

    }

    @GetMapping("/allmovies") //http://localhost:8080/movies/allmovies
    public ResponseEntity<?> getAllMovies(){
        try{
            List<MovieEntity> movieEntityList = movieServiceUserAndAdmin.getAllMovies();
            return new ResponseEntity<>(movieEntityList,HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>("No movies found", HttpStatus.NOT_FOUND);
        }
    }
}
