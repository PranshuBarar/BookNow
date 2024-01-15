package com.example.online_movie_ticketing_application.Services.Impl;

import com.example.online_movie_ticketing_application.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class FetchAuthenticatedUserDetails {
    public static int getCurrentUserId()  {
        CustomUserDetails customUserDetails = getCurrentUserDetails();
        if(customUserDetails != null){
            return customUserDetails.getUserEntity().getId();
        }
        else {
            throw new SessionAuthenticationException("This session is not authenticated");
        }
    }

    public static CustomUserDetails getCurrentUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof CustomUserDetails){
            return (CustomUserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
