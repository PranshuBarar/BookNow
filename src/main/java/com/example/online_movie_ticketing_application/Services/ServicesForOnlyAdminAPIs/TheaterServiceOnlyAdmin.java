package com.example.online_movie_ticketing_application.Services.ServicesForOnlyAdminAPIs;

import com.example.online_movie_ticketing_application.Convertors.TheaterConvertors;
import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import com.example.online_movie_ticketing_application.Entities.TheaterSeatEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TheaterEntryDto;
import com.example.online_movie_ticketing_application.Enums.SeatType;
import com.example.online_movie_ticketing_application.Repository.TheaterRepository;
import com.example.online_movie_ticketing_application.Repository.TheaterSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterServiceOnlyAdmin {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

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

        boolean theaterExists = theaterRepository.existsByName(theaterEntryDto.getName());
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
        int theaterRemoveStatus = theaterRepository.deleteByName(theaterName);
        if(theaterRemoveStatus == 0){
            throw new Exception("Theater not found");
        } else {
            return "Theater removed successfully";
        }
    }

}
