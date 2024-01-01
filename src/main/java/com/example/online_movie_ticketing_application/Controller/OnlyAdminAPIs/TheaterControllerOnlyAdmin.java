package com.example.online_movie_ticketing_application.Controller.OnlyAdminAPIs;

import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;
import com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs.TheaterServiceOnlyAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/theater")
public class TheaterControllerOnlyAdmin {
    @Autowired
    TheaterServiceOnlyAdmin theaterServiceOnlyAdmin;

    @PostMapping("/add") //http://localhost:8080/theater/add
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try {
            String result = theaterServiceOnlyAdmin.addTheater(theaterEntryDto);
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
    public ResponseEntity<?> removeTheater(@RequestParam("theaterName") String theaterName) throws Exception {
        try{
            String response = theaterServiceOnlyAdmin.removeTheater(theaterName);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
