package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
    boolean existsByName(String theaterName);

    TheaterEntity findByName(String theaterName);

    int deleteByTheaterName(String theaterName);
}
