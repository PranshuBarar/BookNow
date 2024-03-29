package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;

public class UserConvertor {

    public static UserEntity convertDtoToEntity(UserRegisterEntryDto userRegisterEntryDto){

        return UserEntity.builder()
                .age(userRegisterEntryDto.getAge())
                .address(userRegisterEntryDto.getAddress())
                .userEmail(userRegisterEntryDto.getEmail())
                .mobNo(userRegisterEntryDto.getMobNo())
                .username(userRegisterEntryDto.getName())
                .password(userRegisterEntryDto.getPassword())
                .role(userRegisterEntryDto.getRole())
                .build();
    }
}
