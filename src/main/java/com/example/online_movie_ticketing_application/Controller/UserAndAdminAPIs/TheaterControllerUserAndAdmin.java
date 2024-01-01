package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.TheaterServiceUserAndAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/theater")
public class TheaterControllerUserAndAdmin {
    @Autowired
    TheaterServiceUserAndAdmin theaterServiceUserAndAdmin;

    @GetMapping("/theaters-with-unique-locations") //http://localhost:8080/theater/theaters-with-unique-locations
    public ResponseEntity<?> theatersWithUniqueLocations(){
        try{
            Map<String,String> theatersWithUniqueLocations = theaterServiceUserAndAdmin.theaterWithUniqueLocations();
            return new ResponseEntity<>(theatersWithUniqueLocations,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
