package com.example.online_movie_ticketing_application.Services.ServicesForUserAndAdminAPIs;


import com.example.online_movie_ticketing_application.Convertors.TicketConvertor;
import com.example.online_movie_ticketing_application.Convertors.UserConvertor;
import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.Entities.ShowSeatEntity;
import com.example.online_movie_ticketing_application.Entities.TicketEntity;
import com.example.online_movie_ticketing_application.Entities.UserEntity;
import com.example.online_movie_ticketing_application.EntryDtos.UserRegisterEntryDto;
import com.example.online_movie_ticketing_application.Repository.ShowRepository;
import com.example.online_movie_ticketing_application.Repository.UserRepository;
import com.example.online_movie_ticketing_application.ResponseDto.TicketDetailsResponseDto;
import com.example.online_movie_ticketing_application.WebSecurityConfig.CurrentSessionUserDetails;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.*;

@Service
public class UserServiceUserAndAdmin {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public String registerUser(UserRegisterEntryDto userRegisterEntryDto) {
        UserEntity userEntity = UserConvertor.convertDtoToEntity(userRegisterEntryDto);
        boolean alreadyExists = userRepository.existsByEmail(userRegisterEntryDto.getEmail());
        if(alreadyExists){
            return "User already exists";
        }

        /*Now since user doesn't exist we will add this new user to the database but before that we will
         * encode the password as we do not want to store the password in the database in form of bare string*/

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userRegisterEntryDto.getPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        return "User added successfully";
    }

    @Transactional
    public String removeUser(){
        UserEntity currentUserEntity = getCurrentUserEntity();
        List<TicketEntity> ticketEntityList = currentUserEntity.getTicketEntityList();
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
        userRepository.deleteById(currentUserEntity.getId());
        return "User deleted successfully";
    }

    public List<TicketDetailsResponseDto> allTickets() throws Exception{
        UserEntity currentUserEntity = getCurrentUserEntity();
        List<TicketEntity> ticketEntityList = currentUserEntity.getTicketEntityList();
        List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = new ArrayList<>();
        for(TicketEntity ticketEntity : ticketEntityList){
            TicketDetailsResponseDto ticketDetailsResponseDto = TicketConvertor.convertEntityToDto(ticketEntity);
            ticketDetailsResponseDtoList.add(ticketDetailsResponseDto);
        }
        return ticketDetailsResponseDtoList;
    }

    public String updateUserAddress(String updatedAddress){
        UserEntity userEntity = getCurrentUserEntity();
        userEntity.setAddress(updatedAddress);
        userRepository.save(userEntity);
        return "User address updated successfully";
    }





//========================================================================================
    /*Below are private functions which can be called by the functions of this class only
    These are not exposed to the Controller class*/

    private UserEntity getCurrentUserEntity(){
        int currentUserId = getCurrentUserId();
        Optional<UserEntity> optionalUserEntity = userRepository.findById(currentUserId);
        if(optionalUserEntity.isPresent()){
            return optionalUserEntity.get();
        } else {
            throw new EntityNotFoundException("No entity with given userId");
        }
    }
    private int getCurrentUserId(){
        CurrentSessionUserDetails currentSessionUserDetails = getCurrentUser();
        if(currentSessionUserDetails != null){
            return currentSessionUserDetails.getId();
        } else{
            throw new RuntimeException("\"currentSessionUserDetails\" not found");
        }
    }
    private CurrentSessionUserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            if(authentication.getPrincipal() instanceof CurrentSessionUserDetails){
                return (CurrentSessionUserDetails)  authentication.getPrincipal();
            }
        }
        return null;
    }
}