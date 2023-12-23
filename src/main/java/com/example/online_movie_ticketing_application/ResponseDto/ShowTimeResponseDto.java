package com.example.online_movie_ticketing_application.ResponseDto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShowTimeResponseDto {
    private List<ShowDateAndTimeResponseDto> showTimeList = new ArrayList<>();
}
