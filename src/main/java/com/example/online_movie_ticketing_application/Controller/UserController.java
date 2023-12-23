package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.EntryDtos.UserDeregisterEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;
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
    public ResponseEntity<String> addUser(@RequestBody UserRegisterEntryDto userRegisterEntryDto) {
        try {
            String response = userService.addUser(userRegisterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(Exception e){
            String result = "User could not be added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
     /*
        //Copy-paste the following in postman
        {
            "name" : "Pranshu",
            "age" : 25,
            "email" : "pranshubarar1851996@gmail.com",
            "mobNo" : "8948607977",
            "address" : "Jhunsi, Allahabad"
            "password" : "Abc12345"
        }
    */

    //Exception handling is required here
    @DeleteMapping("/remove") //http://localhost:8080/users/remove?username=<name here>
    public ResponseEntity<String> removeUser(@RequestBody UserDeregisterEntryDto userDeregisterEntryDto){
        String response = userService.removeUser(userDeregisterEntryDto);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //Exception handling is required here
    @GetMapping("/all-tickets") //http://localhost:8080/users/all-tickets?userId=<id here>
    public ResponseEntity<List<TicketDetailsResponseDto>> allTickets(@RequestParam("email") String email){
        //This will return all tickets booked by user till now...and this includes even cancelled tickets also
        List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = userService.allTickets(email);
        return new ResponseEntity<>(ticketDetailsResponseDtoList,HttpStatus.FOUND);
    }


    //Exception handling is required here
    @PutMapping("/update-address/{email}") //http://localhost:8080/users/update-address/{email}
    public ResponseEntity<String> updateUserAddress(@PathVariable String email, @RequestBody String address){
        String response = userService.updateUserAddress(email,address);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
