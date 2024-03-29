package com.example.online_movie_ticketing_application.Services.Impl;

import com.example.online_movie_ticketing_application.Convertors.TheaterConvertors;
import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import com.example.online_movie_ticketing_application.Entities.TheaterSeatEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;
import com.example.online_movie_ticketing_application.Enums.SeatType;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterSeatRepository;
import com.example.online_movie_ticketing_application.Services.TheaterService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheaterServiceImpl implements TheaterService {

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
                    String name = theaterEntity.getTheaterName();
                    System.out.println("Krishna "+ name);
                    System.out.println(theaterEntity.getLocation());
                    theaterWithUniqueLocations.put(location,name);
                }
            }
            return theaterWithUniqueLocations;
        }
        catch(Exception e){
            throw new EntityNotFoundException("No theater found");
        }

    }


    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{
        /*
         * 1. create theaterSeats
         * 2. I need to save theater : I need theaterEntity
         * 3. Always set the attributes before saving
         * */

        //Some validations required here :-
        if(theaterEntryDto.getName() == null || theaterEntryDto.getLocation() == null){
            throw new Exception("Name and location should be valid");
        }

        boolean theaterExists = theaterRepository.existsByTheaterName(theaterEntryDto.getName());
        if(theaterExists){
            return "Theater already exists";
        }
        TheaterEntity theaterEntity = TheaterConvertors.convertDtoToEntity(theaterEntryDto);
        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto, theaterEntity);
        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);
        theaterRepository.save(theaterEntity);
        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto, TheaterEntity theaterEntity){
        int numberOfClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int numberOfPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        //Create the classic Seats
        classicSeatsCreation(theaterEntity, numberOfClassicSeats, theaterSeatEntityList);

        //Create the premium Seats
        premiumSeatsCreation(theaterEntity, numberOfPremiumSeats, theaterSeatEntityList);

        return theaterSeatEntityList;
    }

    private static void premiumSeatsCreation(TheaterEntity theaterEntity, int numberOfPremiumSeats, List<TheaterSeatEntity> theaterSeatEntityList) {
        for(int count = 1; count<= numberOfPremiumSeats; count++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatType(SeatType.PREMIUM).seatNo(count+"P").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }
    }

    private static void classicSeatsCreation(TheaterEntity theaterEntity, int numberOfClassicSeats, List<TheaterSeatEntity> theaterSeatEntityList) {

        for(int count = 1; count<= numberOfClassicSeats; count++){
            //We need to make a new TheaterSeatEntity
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }
    }

    @Transactional
    public String removeTheater(String theaterName) throws Exception {
        int theaterRemoveStatus = theaterRepository.deleteByTheaterName(theaterName);
        if(theaterRemoveStatus == 0){
            throw new Exception("Theater not found");
        } else {
            return "Theater removed successfully";
        }
    }

}
