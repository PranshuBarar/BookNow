package com.example.online_movie_ticketing_application.Convertors;

import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import com.example.online_movie_ticketing_application.EntryDtos.ShowEntryDto;

public class ShowConvertors {
    public static ShowEntity convertEntrytoEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity = ShowEntity.builder()
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType()).build();

        return showEntity;
    }

}
