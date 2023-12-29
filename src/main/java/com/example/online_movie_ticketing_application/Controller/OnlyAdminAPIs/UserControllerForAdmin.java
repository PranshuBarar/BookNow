package com.example.online_movie_ticketing_application.Controller.OnlyAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.LoggedInUsers.UserServiceUserAndAdmin;
import com.example.online_movie_ticketing_application.WebSecurityConfig.CurrentSessionUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/users")
public class UserControllerForAdmin {
    @Autowired
    UserServiceUserAndAdmin userServiceUserAndAdmin;

    @Autowired
    SessionRegistry sessionRegistry;

    @GetMapping("/get-all-users") //http://localhost:8080/admin/users/get-all-users
    public ResponseEntity<?> getAllUsers(){
        try{
            List<UserEntity> userEntityList = userServiceUserAndAdmin.getAllUsers();
            return new ResponseEntity<>(userEntityList, HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loggedUsers")
    public List<String> getUsersFromSessionRegistry() {
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
}
