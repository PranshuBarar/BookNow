package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.CustomExceptions.ShowAlreadyExistsException;
import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.Enums.ShowCancellationResponse;
import com.example.online_movie_ticketing_application.ResponseDto.AvailableSeatsResponseDto;
import com.example.online_movie_ticketing_application.Services.Impl.ShowServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
@SecurityRequirement(name = "Bearer Authentication")
public class ShowController {
    @Autowired
    ShowServiceImpl showServiceImpl;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping("/add") //http://localhost:8080/admin/show/add
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto) {
        try{
            String response = showServiceImpl.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(ShowAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    /*
        //Copy-paste the following in postman
        {
            "showDate" : "2023-02-19",
            "showTime": "16:00:00.000",
            "showType" : "_2D",
            "movieName" : "Inception",
            "theaterName" : "PVR",
            "classicSeatPrice" : 25,
            "premiumSeatPrice" : 35
        }
    */


    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @DeleteMapping("/cancel") //http://localhost:8080/show/cancel?showId=<id here>
    public ResponseEntity<?> cancelShow(@RequestBody ShowDateAndTimeEntryDto showDateAndTimeEntryDto){
        try{
            ShowCancellationResponse response = showServiceImpl.cancelShow(showDateAndTimeEntryDto);
            if(response.equals(ShowCancellationResponse.SHOW_IS_CANCELED_SUCCESSFULLY)){
                //Email will be sent to the users that show has been cancelled,
                //you will be refunded your money back
            }
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/available-seats")
    public ResponseEntity<?> getAvailableSeats(@RequestBody ShowDateAndTimeEntryDto showDateAndTimeEntryDto){
        try{
            List<AvailableSeatsResponseDto> availableSeatsResponseDtoList= showServiceImpl.getAvailableSeats(showDateAndTimeEntryDto);
            return new ResponseEntity<>(availableSeatsResponseDtoList, HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /*
      {
        "theaterName": "PVR",
        "showDate": "2023-02-19",
        "showTime": "16:00:00.000"
      }
    */
}
