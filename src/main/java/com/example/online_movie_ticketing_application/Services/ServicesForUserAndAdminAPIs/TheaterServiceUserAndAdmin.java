package com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs;

import com.example.online_movie_ticketing_application.Convertors.TheaterConvertors;
import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import com.example.online_movie_ticketing_application.Entities.TheaterSeatEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;
import com.example.online_movie_ticketing_application.Enums.SeatType;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterSeatRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheaterServiceUserAndAdmin {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public Map<String,String> theaterWithUniqueLocations(){
        Map<String,String> theaterWithUniqueLocations = new HashMap<>();
        try{
            List<TheaterEntity> theaterEntityList = theaterRepository.findAll();
            for(TheaterEntity theaterEntity : theaterEntityList){
                String location = theaterEntity.getLocation();
                if(!theaterWithUniqueLocations.containsKey(location)){
                    String name = theaterEntity.getName();
                    theaterWithUniqueLocations.put(location,name);
                }
            }
            return theaterWithUniqueLocations;
        }
        catch(Exception e){
            throw new EntityNotFoundException("No theater found");
        }

    }

}
