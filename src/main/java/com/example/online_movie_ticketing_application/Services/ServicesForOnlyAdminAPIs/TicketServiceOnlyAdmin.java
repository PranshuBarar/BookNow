package com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs;

import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.TicketRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceOnlyAdmin {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public List<TicketEntity> getTicketsBookedByUser(String userEmail){
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        return userEntity.getTicketEntityList();
    }
}
