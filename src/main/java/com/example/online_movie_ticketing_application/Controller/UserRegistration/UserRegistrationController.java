package com.example.online_movie_ticketing_application.Controller.UserRegistration;

import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRegistrationController {
    @Autowired
    UserService userService;

    @PostMapping("/add") //http://localhost:8080/users/add
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterEntryDto userRegisterEntryDto) {
        try {
            String response = userService.registerUser(userRegisterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
     /*
        //Copy-paste the following in postman
        {
            "name" : "Pranshu",
            "age" : 25,
            "email" : "pranshubarar1851996@gmail.com",
            "mobNo" : "8948607977",
            "address" : "Jhunsi, Allahabad",
            "password" : "test12345"
        }
    */
}
