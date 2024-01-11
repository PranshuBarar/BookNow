package com.example.online_movie_ticketing_application.CustomExceptions;


public class NoMovieException extends Exception{
    public NoMovieException(String message){
        super(message);
    }
}
