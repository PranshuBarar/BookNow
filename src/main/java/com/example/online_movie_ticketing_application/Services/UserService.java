package com.example.online_movie_ticketing_application.Services;


import com.example.online_movie_ticketing_application.Convertors.UserConvertor;
import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserEntryDto;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) {
        UserEntity userEntity = UserConvertor.convertDtoToEntity(userEntryDto);
        boolean alreadyExists = userRepository.existsByEmail(userEntryDto.getEmail());
        if(alreadyExists){
            return "User already exists";
        }
        userRepository.save(userEntity);
        return "User added successfully";
    }
}