package com.example.online_movie_ticketing_application.EntryDtos;

import com.example.online_movie_ticketing_application.Enums.Role;
import lombok.Data;

@Data
public class UserRegisterEntryDto {

    private String name;

    private int age;

    private String email;

    private String mobNo;

    private String address;

    private String password;

    private Role role;
}
