package com.example.online_movie_ticketing_application.config;

import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
@RequestMapping("/api")
//@CrossOrigin
public class UserAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Operation(summary = "Authenticate to get access JWT token for USER")
    @PostMapping("/auth/login/user")
    public ResponseEntity<?> createAuthenticationTokenForUser(@RequestBody JwtRequest jwtRequest) throws Exception {
        String userEmail = jwtRequest.getUserEmail();
        String password = jwtRequest.getPassword();
        try{
            authenticate(userEmail,password);
        } catch (Exception e) {
            return new ResponseEntity<>("Wrong username or password", HttpStatus.UNAUTHORIZED);
        }
        //If no exception thrown after running authenticate(), it means request is authenticated

        final CustomUserDetails customUserDetails = jwtUserDetailsServiceImpl.loadUserByUsername(userEmail);
        final String token = jwtTokenUtil.generateToken(customUserDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Operation(summary = "Authenticate to get access JWT token for ADMIN")
    @PostMapping("/auth/login/admin")
    public ResponseEntity<?> createAuthenticationTokenForAdmin(@RequestBody JwtRequest jwtRequest) throws Exception {
        String userEmail = jwtRequest.getUserEmail();
        String password = jwtRequest.getPassword();
        try{
            authenticate(userEmail,password);
        } catch (Exception e) {
            return new ResponseEntity<>("Wrong username or password", HttpStatus.UNAUTHORIZED);
        }
        //If no exception thrown after running authenticate(), it means request is authenticated

        final CustomUserDetails customUserDetails = jwtUserDetailsServiceImpl.loadUserByUsername(userEmail);
        final String token = jwtTokenUtil.generateToken(customUserDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Operation(summary = "Register here to explore the Application APIs")
    @PostMapping("/auth/signup/user")
    public ResponseEntity<?> saveUser(@RequestBody UserRegisterEntryDto userRegisterEntryDto) throws Exception {
        try{
            String response = jwtUserDetailsServiceImpl.saveUser(userRegisterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("User already Exists", HttpStatus.BAD_REQUEST);
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

    @Operation(summary = "Register here to explore the Application APIs")
    @PostMapping("/auth/signup/admin")
    public ResponseEntity<?> saveAdmin(@RequestBody UserRegisterEntryDto userRegisterEntryDto) throws Exception {
        try{
            String response = jwtUserDetailsServiceImpl.saveAdmin(userRegisterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("User already Exists", HttpStatus.BAD_REQUEST);
        }
    }



    private void authenticate(String userEmail, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, password));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
