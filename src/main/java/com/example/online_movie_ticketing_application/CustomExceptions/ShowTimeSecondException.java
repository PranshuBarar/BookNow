package com.example.online_movie_ticketing_application.CustomExceptions;

import lombok.Data;


public class ShowTimeSecondException extends Exception{
    public ShowTimeSecondException(String message){
        super(message);
    }
}
