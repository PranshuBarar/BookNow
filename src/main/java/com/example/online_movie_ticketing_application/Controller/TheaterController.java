package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;
import com.example.online_movie_ticketing_application.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping("/add") //http://localhost:8080/theater/add
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try {
            String result = theaterService.addTheater(theaterEntryDto);
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

    @DeleteMapping("/remove") //http://localhost:8080/theater/remove?theaterId=<id here>
    public ResponseEntity<String> removeTheater(@RequestParam("theaterId") int theaterId){
        String response = theaterService.removeTheater(theaterId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/theaters-with-unique-locations") //http://localhost:8080/theater/theaters-with-unique-locations
    public ResponseEntity<Map<String,String>> theatersWithUniqueLocations(){
        Map<String,String> theatersWithUniqueLocations = theaterService.theaterWithUniqueLocations();
        return new ResponseEntity<>(theatersWithUniqueLocations,HttpStatus.FOUND);
    }
}
