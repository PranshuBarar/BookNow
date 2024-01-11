package com.example.online_movie_ticketing_application.CustomExceptions;


public class NoShowException extends Exception{
    public NoShowException(String message){
        super(message);
    }
}
