package com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs;


import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import com.example.online_movie_ticketing_application.WebSecurityConfig.CurrentSessionUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceOnlyAdmin {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRegistry sessionRegistry;

    public List<String> getLoggedInUsers(){
        List<String> loggedInUsers = sessionRegistry.getAllPrincipals().stream()
                .filter(loggedUser -> {
                    List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(loggedUser, false);
                    if (!sessionInformationList.isEmpty()) {
                        SessionInformation sessionInformation = sessionInformationList.get(0);
                        System.out.println("User: " + ((CurrentSessionUserDetails) loggedUser).getUsername() +
                                " - Session Active: " + !sessionInformation.isExpired());
                        return !sessionInformation.isExpired();
                    }
                    return false;
                })
                .map(loggedUser -> ((CurrentSessionUserDetails) loggedUser).getUsername())
                .collect(Collectors.toList());

        System.out.println("Currently Logged-In Users: " + loggedInUsers);

        return loggedInUsers;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
}