package com.example.online_movie_ticketing_application.Controller.UserRegistration;

import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.LoggedInUsers.UserServiceUserAndAdmin;
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
    UserServiceUserAndAdmin userServiceUserAndAdmin;

    @PostMapping("/add") //http://localhost:8080/users/add
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterEntryDto userRegisterEntryDto) {
        try {
            String response = userServiceUserAndAdmin.registerUser(userRegisterEntryDto);
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
            "address" : "Jhunsi, Allahabad",
            "password" : "test12345"
        }
    */
}
