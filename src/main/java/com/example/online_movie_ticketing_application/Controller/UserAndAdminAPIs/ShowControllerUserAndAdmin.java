package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.AvailableSeatsResponseDto;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.ShowServiceUserAndAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowControllerUserAndAdmin {
    @Autowired
    ShowServiceUserAndAdmin showServiceUserAndAdmin;

    @GetMapping("/available-seats")
    public ResponseEntity<?> getAvailableSeats(@RequestBody ShowDateAndTimeEntryDto showDateAndTimeEntryDto){
        try{
            List<AvailableSeatsResponseDto> availableSeatsResponseDtoList= showServiceUserAndAdmin.getAvailableSeats(showDateAndTimeEntryDto);
            return new ResponseEntity<>(availableSeatsResponseDtoList, HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
