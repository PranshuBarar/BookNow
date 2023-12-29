package com.example.online_movie_ticketing_application.Controller.OnlyAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs.TicketServiceOnlyAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/ticket")
public class TicketControllerForAdmin {

    @Autowired
    TicketServiceOnlyAdmin ticketServiceOnlyAdmin;

    public ResponseEntity<?> getTicketsBookedByUser(@RequestParam String userEmail){
        List<TicketEntity> ticketEntityList = ticketServiceOnlyAdmin.getTicketsBookedByUser(userEmail);
        if(ticketEntityList.isEmpty()){
            return new ResponseEntity<>("No tickets booked by this user yet", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(ticketEntityList, HttpStatus.FOUND);
    }
}
