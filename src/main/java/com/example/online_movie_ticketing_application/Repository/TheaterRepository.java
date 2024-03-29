package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
    boolean existsByTheaterName(String name);

    TheaterEntity findByTheaterName(String name);

    int deleteByTheaterName(String name);

}
