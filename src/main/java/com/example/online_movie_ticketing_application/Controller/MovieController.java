package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add") //http://localhost:8080/movies/add
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try {
            String result = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e){
            String response = "Movie not added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    /*
        //Copy-paste the following in postman
        {
           "movieName" : "Pathan",
           "ratings" : 7.6,
           "duration" : 3,
           "language" : "HINDI",
           "genre" : "ACTION"
        }
    */

    @DeleteMapping("/remove") //http://localhost:8080/movies/remove
    /*This API will be called in the case if movie gets banned by the Govt. In that particular case
    * movie will be removed from the movie database, it means all the shows of this movie will be cancelled and
    * subsequently all the tickets will get cancelled and amount will be refunded to the user.
    * */
    public ResponseEntity<String> removeMovie(@RequestParam("movieId") int movieId){
        String response = movieService.removeMovie(movieId);
        //Now here we will email to all the users that their tickets has been cancelled and they will be refunded
        //back their amounts
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
