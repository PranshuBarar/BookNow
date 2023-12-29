package com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs;


import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceOnlyAdmin {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public List<?> getLoggedInUsers(){
        return null;
    }
}