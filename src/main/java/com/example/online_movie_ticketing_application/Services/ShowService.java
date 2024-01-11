package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.CustomExceptions.ShowAlreadyExistsException;
import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.Enums.ShowCancellationResponse;
import com.example.online_movie_ticketing_application.ResponseDto.AvailableSeatsResponseDto;

import java.util.List;

public interface ShowService {
    List<AvailableSeatsResponseDto> getAvailableSeats(ShowDateAndTimeEntryDto showDateAndTimeEntryDto);

    String addShow(ShowEntryDto showEntryDto) throws ShowAlreadyExistsException;

    ShowCancellationResponse cancelShow(ShowDateAndTimeEntryDto showDateAndTimeEntryDto) throws Exception;
}
