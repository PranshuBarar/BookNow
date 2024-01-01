package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;

import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.UserServiceUserAndAdmin;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerUserAndAdmin {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserServiceUserAndAdmin userServiceUserAndAdmin;

    @DeleteMapping("/remove") //http://localhost:8080/users/remove
    public ResponseEntity<String> removeUser(){
        try{
            String response = userServiceUserAndAdmin.removeUser();
            httpServletRequest.getSession().invalidate();
            String sessionId = httpServletRequest.getSession().getId();
            return new ResponseEntity<>(response+" (Session with Session Id: "+ sessionId +  " got invalidated)", HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-tickets") //http://localhost:8080/users/all-tickets
    public ResponseEntity<?> allTickets(){
        //This will return all tickets booked by user till now...and this includes even cancelled tickets also
        try{
            List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = userServiceUserAndAdmin.allTickets();
            return new ResponseEntity<>(ticketDetailsResponseDtoList,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update-address") //http://localhost:8080/users/update-address
    public ResponseEntity<?> updateUserAddress(@RequestBody String address){
        try{
            String response = userServiceUserAndAdmin.updateUserAddress(address);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
