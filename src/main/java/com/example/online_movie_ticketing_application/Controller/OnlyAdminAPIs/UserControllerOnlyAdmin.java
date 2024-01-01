package com.example.online_movie_ticketing_application.Controller.OnlyAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs.UserServiceOnlyAdmin;
import com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs.UserServiceUserAndAdmin;
import com.example.online_movie_ticketing_application.WebSecurityConfig.CurrentSessionUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/users")
public class UserControllerOnlyAdmin {
    @Autowired
    UserServiceOnlyAdmin userServiceOnlyAdmin;

    @GetMapping("/get-all-users") //http://localhost:8080/admin/users/get-all-users
    public ResponseEntity<?> getAllUsers(){
        try{
            List<UserEntity> userEntityList = userServiceOnlyAdmin.getAllUsers();
            return new ResponseEntity<>(userEntityList, HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loggedUsers")
    public ResponseEntity<?> getUsersFromSessionRegistry() {
        List<String> loggedInUsersList = userServiceOnlyAdmin.getLoggedInUsers();
        return new ResponseEntity<>(loggedInUsersList,HttpStatus.FOUND);
    }
}
