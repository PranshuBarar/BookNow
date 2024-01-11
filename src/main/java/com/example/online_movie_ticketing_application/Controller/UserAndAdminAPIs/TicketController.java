package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TicketEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;
import com.example.online_movie_ticketing_application.Services.Impl.TicketServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@SecurityRequirement(name = "Bearer Authentication")
public class TicketController {
    @Autowired
    TicketServiceImpl ticketServiceImpl;

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTicketsBookedByUser(@RequestParam String userEmail){
        List<TicketEntity> ticketEntityList = ticketServiceImpl.getTicketsBookedByUser(userEmail);
        if(ticketEntityList.isEmpty()){
            return new ResponseEntity<>("No tickets booked by this user yet", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(ticketEntityList, HttpStatus.FOUND);
    }


    @PostMapping("/booking_ticket") //http://localhost:8080/tickets/booking_ticket
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto) throws Exception {
        String response = ticketServiceImpl.bookTicket(ticketEntryDto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    /*
        //Copy-paste the following in postman
        {
            "theaterName" : <theaterName here>,
            "showDate" : <showDate here>,
            "showTime" : <showTime here>,
            "requestedSeats" : ["16P","13C"]
        }
    */

    //Exception handling is required here. If no ticket with this ticket id it should throw some message
    @GetMapping("/get-ticket-details") //http://localhost:8080/tickets/get-ticket-details?ticketId=<id here>
    public ResponseEntity<?> getDetails(@RequestParam("ticketUUID") String ticketUUID){
        try{
            TicketDetailsResponseDto ticketDetailsResponseDto = ticketServiceImpl.getDetails(ticketUUID);
            return new ResponseEntity<>(ticketDetailsResponseDto,HttpStatus.FOUND);
        } catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    //Exception handling is requried here
    @DeleteMapping("/cancel_ticket") //http://localhost:8080/tickets/cancel_ticket?ticketId=<id here>
    public ResponseEntity<String> cancelTicket(@RequestParam("ticketId") int ticketId){
        String response = ticketServiceImpl.cancelTicket(ticketId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
