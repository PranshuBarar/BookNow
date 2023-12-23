package com.example.online_movie_ticketing_application.Controller;

import com.example.online_movie_ticketing_application.EntryDtos.ShowDateAndTimeEntryDto;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;
import com.example.online_movie_ticketing_application.Enums.ShowCancellationResponse;
import com.example.online_movie_ticketing_application.ResponseDto.ShowDateAndTimeResponseDto;
import com.example.online_movie_ticketing_application.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @GetMapping("/hello") //http://localhost:8080/show/hello
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello", HttpStatus.ACCEPTED);
    }

    @PostMapping("/add") //http://localhost:8080/show/add
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        String response = showService.addShow(showEntryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
        //Copy-paste the following in postman
        {
            "showDate" : "2023-02-19",
            "showTime": "16:00:00.000",
            "showType" : "_2D",
            "movieId" : 1,
            "theaterId" : 1,
            "classicSeatPrice" : 25,
            "premiumSeatPrice" : 35
        }
    */
    //Exception handling is required here as I am sending a random showId with actually
    //no show with that id and still I think its doing something but in response there is no
    //message at all that 'now show with such id' etc
    @DeleteMapping("/cancel") //http://localhost:8080/show/cancel?showId=<id here>
    public ResponseEntity<?> cancelShow(@RequestBody ShowDateAndTimeEntryDto showDateAndTimeEntryDto){
        try{
            ShowCancellationResponse response = showService.cancelShow(showDateAndTimeEntryDto);
            if(response.equals(ShowCancellationResponse.SHOW_IS_CANCELED_SUCCESSFULLY)){
                //Email will be sent to the users that show has been cancelled,
                //you will be refunded your money back
            }
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
