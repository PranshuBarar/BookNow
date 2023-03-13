package com.example.online_movie_ticketing_application.Services;


import com.example.online_movie_ticketing_application.Convertors.UserConvertor;
import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.Entities.ShowSeatEntity;
import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserEntryDto;
import com.example.online_movie_ticketing_application.Enums.TicketStatus;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public String addUser(UserEntryDto userEntryDto) {
        UserEntity userEntity = UserConvertor.convertDtoToEntity(userEntryDto);
        boolean alreadyExists = userRepository.existsByEmail(userEntryDto.getEmail());
        if(alreadyExists){
            return "User already exists";
        }
        userRepository.save(userEntity);
        return "User added successfully";
    }

    public String removeUser(int userId){
        UserEntity userEntity = userRepository.findById(userId).get();
        List<TicketEntity> ticketEntityList = userEntity.getTicketEntityList();
        for(TicketEntity ticketEntity : ticketEntityList){
            String bookedSeats = ticketEntity.getBookedSeats();
            String [] bookedSeatsArr = bookedSeats.split(", ");
            if(ticketEntity.getShowTime().compareTo(LocalTime.now())<0){
                continue;
            }
            ShowEntity showEntity = ticketEntity.getShowEntity();
            List<ShowSeatEntity> showSeatEntityList = showEntity.getShowSeatEntityList();
            for(ShowSeatEntity showSeatEntity : showSeatEntityList){
                String seatNo = showSeatEntity.getSeatNo();
                if(Arrays.asList(bookedSeatsArr).contains(seatNo)){
                    showSeatEntity.setBooked(false);
                }
            }
            showRepository.save(showEntity);
        }
        userRepository.deleteById(userId);
        return "User deleted successfully";
    }

    public List<TicketEntity> allTickets(int userId){
        UserEntity userEntity = userRepository.findById(userId).get();
        return userEntity.getTicketEntityList();
    }
}