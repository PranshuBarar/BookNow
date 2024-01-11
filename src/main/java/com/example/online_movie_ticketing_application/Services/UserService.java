package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;

import java.util.List;

public interface UserService {
    String registerUser(UserRegisterEntryDto userRegisterEntryDto);

    String removeUser();

    List<TicketDetailsResponseDto> allTicketsOfCurrentUser() throws Exception;

    String updateUserAddress(String updatedAddress);

    List<UserEntity> getAllUsers();
}
