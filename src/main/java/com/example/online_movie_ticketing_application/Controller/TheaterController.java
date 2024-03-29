package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;
import com.example.online_movie_ticketing_application.Services.Impl.TheaterServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/theater")
@SecurityRequirement(name = "Bearer Authentication")
public class TheaterController {
    @Autowired
    TheaterServiceImpl theaterServiceImpl;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping("/add") //http://localhost:8080/theater/add
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try {
            String result = theaterServiceImpl.addTheater(theaterEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
        //Copy-paste the following in postman
        {
            "name":"PVR",
            "location":"Civil Lines, Allahabad",
            "classicSeatsCount" : 7,
            "premiumSeatsCount" : 7
        }
    */


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/remove") //http://localhost:8080/theater/remove?theaterId=<id here>
    public ResponseEntity<?> removeTheater(@RequestParam("theaterName") String theaterName) throws Exception {
        try{
            String response = theaterServiceImpl.removeTheater(theaterName);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/theaters-with-unique-locations") //http://localhost:8080/theater/theaters-with-unique-locations
    public ResponseEntity<?> theatersWithUniqueLocations(){
        try{
            Map<String,String> theatersWithUniqueLocations = theaterServiceImpl.theaterWithUniqueLocations();
            return new ResponseEntity<>(theatersWithUniqueLocations,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
