package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
    long countByTheaterEntityIdAndShowDateAndShowTime(int theater_entity_id, LocalDate showDate, LocalTime showTime);
}
