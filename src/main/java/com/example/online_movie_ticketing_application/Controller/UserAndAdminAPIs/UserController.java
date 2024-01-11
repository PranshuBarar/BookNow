package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;
import com.example.online_movie_ticketing_application.Services.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserServiceImpl userServiceImpl;

    @DeleteMapping("/remove") //http://localhost:8080/users/remove
    public ResponseEntity<String> removeUser(){
        try{
            String response = userServiceImpl.removeUser();
            httpServletRequest.getSession().invalidate();
            String sessionId = httpServletRequest.getSession().getId();
            return new ResponseEntity<>(response+" (Session with Session Id: "+ sessionId +  " got invalidated)", HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-tickets") //http://localhost:8080/users/all-tickets
    public ResponseEntity<?> allTicketsOfCurrentUser(){
        //This will return all tickets booked by user till now...and this includes even cancelled tickets also
        try{
            List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = userServiceImpl.allTicketsOfCurrentUser();
            return new ResponseEntity<>(ticketDetailsResponseDtoList,HttpStatus.FOUND);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update-address") //http://localhost:8080/users/update-address
    public ResponseEntity<?> updateUserAddress(@RequestBody String address){
        try{
            String response = userServiceImpl.updateUserAddress(address);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all-users") //http://localhost:8080/admin/users/get-all-users
    public ResponseEntity<?> getAllUsers(){
        try{
            List<UserEntity> userEntityList = userServiceImpl.getAllUsers();
            return new ResponseEntity<>(userEntityList, HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
