package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserEntryDto;
import com.example.online_movie_ticketing_application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add") //http://localhost:8080/users/add
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(Exception e){
            String result = "User could not be added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
     /*
        //Copy-paste the following in postman
        "name" : "Pranshu",
        "age" : 25,
        "email" : "pranshubarar1851996@gmail.com",
        "mobNo" : "8948607977",
        "address" : "Jhunsi, Allahabad"
    */

    @DeleteMapping("/remove") //http://localhost:8080/users/remove
    public ResponseEntity<String> removeUser(@RequestParam("userId") int userId){
        String response = userService.removeUser(userId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-tickets")
    public ResponseEntity<List<TicketEntity>> allTickets(@RequestParam("userId") int userId){
        //This will return all tickets booked by user till now...and this includes even cancelled tickets also
        List<TicketEntity> ticketEntityList = userService.allTickets(userId);
        return new ResponseEntity<>(ticketEntityList,HttpStatus.FOUND);
    }
}
