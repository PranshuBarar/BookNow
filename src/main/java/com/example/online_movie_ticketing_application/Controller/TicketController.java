package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TicketEntryDto;
import com.example.online_movie_ticketing_application.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/booking_ticket") //http://localhost:8080/tickets/booking_ticket
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto) throws Exception {
        String response = ticketService.bookTicket(ticketEntryDto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    /*
        //Copy-paste the following in postman
        {
            "showId" : 2,
            "userId" : 1,
            "requestedSeats" : ["16P","13C"]
        }
    */

    @GetMapping("/get-ticket-details") //http://localhost:8080/tickets/get-ticket-details?tickedId=<id here>
    public ResponseEntity<TicketEntity> getDetails(@RequestParam("ticketId") int ticketId){
        TicketEntity ticketEntity = ticketService.getDetails(ticketId);
        return new ResponseEntity<>(ticketEntity,HttpStatus.FOUND);
    }

    @DeleteMapping("/cancel_ticket") //http://localhost:8080/tickets/cancel_ticket?ticketId=<id here>
    public ResponseEntity<String> cancelTicket(@RequestParam("ticketId") int ticketId){
        String response = ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
