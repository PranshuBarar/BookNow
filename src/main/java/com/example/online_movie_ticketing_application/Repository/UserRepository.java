package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByEmail(String email);
    UserEntity findByName(String username);

    UserEntity findByEmail(String email);
}
