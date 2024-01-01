package com.example.online_movie_ticketing_application.CustomExceptions;

import lombok.Data;


public class ShowTimeFirstException extends Exception{
    public ShowTimeFirstException(String message){
        super(message);
    }
}
