package com.example.online_movie_ticketing_application.Controller.OnlyAdminAPIs;

import com.example.online_movie_ticketing_application.EntryDtos.MovieEntryDto;
import com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs.MovieServiceOnlyAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/movies")
public class MovieControllerForAdmin {

    @Autowired
    MovieServiceOnlyAdmin movieServiceOnlyAdmin;

    @PostMapping("/add") //http://localhost:8080/movies/add
    public ResponseEntity<?> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try{
            String result = movieServiceOnlyAdmin.addMovie(movieEntryDto);
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

    @DeleteMapping("/remove") //http://localhost:8080/movies/remove?movieName=<name_here>
    public ResponseEntity<String> removeMovie(@RequestParam("movieName") String movieName) {
        try{
            String response = movieServiceOnlyAdmin.removeMovie(movieName);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>("Movie with this name: \"" + movieName + "\" does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
