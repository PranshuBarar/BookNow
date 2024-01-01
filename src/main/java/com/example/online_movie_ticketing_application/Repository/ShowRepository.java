package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
    boolean existsByTheaterEntityIdAndShowDateAndShowTime(int theater_entity_id, LocalDate showDate, LocalTime showTime);

    @Query(value = "SELECT * FROM shows where theater_entity_id = :theaterEntityId AND show_date = :showDate AND show_time = :showTime", nativeQuery = true)
    ShowEntity findByTheaterEntityIdAndShowDateAndShowTime(LocalDate showDate, LocalTime showTime, int theaterEntityId);
}
