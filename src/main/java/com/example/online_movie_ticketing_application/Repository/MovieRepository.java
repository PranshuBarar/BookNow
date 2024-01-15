package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {


    boolean existsByMovieName(String movieName);

    Integer deleteByMovieName(String movieName);

    MovieEntity findByMovieName(String movieName);
}
