package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserEntryDto;

public class UserConvertor {

    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){

        return UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress())
                .email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).name(userEntryDto.getName()).build();
    }
}
