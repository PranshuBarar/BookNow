package com.example.online_movie_ticketing_application.Services;

import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.Entities.ShowSeatEntity;
import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.TicketEntryDto;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.TicketRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception {
        int showId = ticketEntryDto.getShowId();
        int userId = ticketEntryDto.getUserId();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        ShowEntity showEntity = showRepository.findById(showId).get();

        List<ShowSeatEntity> listOfSeatsForThisShow = showEntity.getShowSeatEntityList();

        String bookedSeats;
        int totalAmount;
        try{
            Pair<String,Integer> pair = bookTheSeats(requestedSeats, listOfSeatsForThisShow);
            bookedSeats = pair.getFirst();
            totalAmount = pair.getSecond();
        } catch (Exception e){
            return "Seat is already booked";
        }

        UserEntity userEntity = userRepository.findById(userId).get();
        String movieName = showEntity.getMovieEntity().getMovieName();
        String theaterName = showEntity.getTheaterEntity().getName();
        String ticketId = UUID.randomUUID().toString();
        LocalDate localDate = showEntity.getShowDate();
        LocalTime localTime = showEntity.getShowTime();

        TicketEntity ticketEntity = TicketEntity.builder().ticketId(ticketId).movieName(movieName)
                                    .showEntity(showEntity).theaterName(theaterName).bookedSeats(bookedSeats)
                                    .showDate(localDate).showTime(localTime).userEntity(userEntity).totalAmount(totalAmount)
                                    .build();

        userEntity.getTicketEntityList().add(ticketEntity);
        userRepository.save(userEntity);

        entityManager.detach(showEntity);
        entityManager.flush();
        showEntity.getTicketEntityList().add(ticketEntity);
        showRepository.save(showEntity);
        return bookedSeats;
    }

    private Pair<String,Integer> bookTheSeats(List<String> requestedSeats,
                                              List<ShowSeatEntity> listOfSeatsForThisShow)
                                              throws Exception {

        for(ShowSeatEntity showSeatEntity : listOfSeatsForThisShow) {
            String seatNo = showSeatEntity.getSeatNo();
            if (requestedSeats.contains(seatNo)) {
                if (showSeatEntity.isBooked()) {
                    throw new Exception();
                }
            }
        }

        StringBuilder bookedSeats = new StringBuilder();
        int totalAmount = 0;
        for(ShowSeatEntity showSeatEntity : listOfSeatsForThisShow){
            String seatNo = showSeatEntity.getSeatNo();
            if (requestedSeats.contains(seatNo)){
                totalAmount += showSeatEntity.getPrice();
                if(bookedSeats.length()==0){
                    bookedSeats.append(seatNo);
                }
                else {
                    bookedSeats.append(", ").append(seatNo);
                }
                showSeatEntity.setBooked(true);
            }
        }
        return Pair.of(bookedSeats.toString(),totalAmount);
    }
}
