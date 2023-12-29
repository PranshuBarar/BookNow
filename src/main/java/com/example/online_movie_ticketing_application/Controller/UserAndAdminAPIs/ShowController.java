package com.example.online_movie_ticketing_application.Controller.UserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.ShowServiceUserAndAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowServiceUserAndAdmin showServiceUserAndAdmin;

    @GetMapping("/hello") //http://localhost:8080/show/hello
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello", HttpStatus.ACCEPTED);
    }
}
