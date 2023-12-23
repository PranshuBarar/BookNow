package com.example.online_movie_ticketing_application.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDeregisterEntryDto {
    private String username;
    private String email;
}
