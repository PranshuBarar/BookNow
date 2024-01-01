package com.example.online_movie_ticketing_application.CustomExceptions;

import lombok.Data;

public class ShowAlreadyExistsException extends Exception{
    String message = "Show already exists at give date and time";
}
