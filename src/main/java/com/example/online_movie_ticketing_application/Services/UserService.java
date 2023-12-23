package com.example.online_movie_ticketing_application.Services;


import com.example.online_movie_ticketing_application.Convertors.TicketConvertor;
import com.example.online_movie_ticketing_application.Convertors.UserConvertor;
import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.Entities.ShowSeatEntity;
import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserDeregisterEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public String addUser(UserRegisterEntryDto userRegisterEntryDto) {
        UserEntity userEntity = UserConvertor.convertDtoToEntity(userRegisterEntryDto);
        boolean alreadyExists = userRepository.existsByEmail(userRegisterEntryDto.getEmail());
        if(alreadyExists){
            return "User already exists";
        }
        /*
        * Now since user doesn't exists we will add this new user to the database but before that we will
        * encode the password as we do not want to store the password in the database in form of bare string
        * */

        userRepository.save(userEntity);
        return "User added successfully";
    }

    @Transactional
    public String removeUser(UserDeregisterEntryDto userDeregisterEntryDto){
        String username = userDeregisterEntryDto.getUsername();
        UserEntity userEntity;
        try{
            userEntity = userRepository.findByName(username);
        } catch(Exception e){
            throw new EntityNotFoundException("No account with this username found");
        }
        List<TicketEntity> ticketEntityList = userEntity.getTicketEntityList();
        for(TicketEntity ticketEntity : ticketEntityList){
            String bookedSeats = ticketEntity.getBookedSeats();
            String [] bookedSeatsArr = bookedSeats.split(", ");
            if(ticketEntity.getShowTime().isBefore(LocalTime.now())){
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
        userRepository.deleteById(userEntity.getId());
        return "User deleted successfully";
    }

    public List<TicketDetailsResponseDto> allTickets(String email){
        UserEntity userEntity = userRepository.findByEmail(email);
        List<TicketEntity> ticketEntityList = userEntity.getTicketEntityList();
        List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = new ArrayList<>();
        for(TicketEntity ticketEntity : ticketEntityList){
            TicketDetailsResponseDto ticketDetailsResponseDto = TicketConvertor.convertEntityToDto(ticketEntity);
            ticketDetailsResponseDtoList.add(ticketDetailsResponseDto);
        }
        return ticketDetailsResponseDtoList;
    }

    public String updateUserAddress(String email, String address){
        UserEntity userEntity = userRepository.findByName(email);
        userEntity.setAddress(address);
        userRepository.save(userEntity);
        return "User address updated successfully";
    }
}