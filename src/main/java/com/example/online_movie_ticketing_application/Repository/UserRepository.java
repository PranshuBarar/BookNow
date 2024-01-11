package com.example.online_movie_ticketing_application.Repository;

import com.example.online_movie_ticketing_application.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByUserEmail(String userEmail);
    UserEntity findByUsername(String username);

    UserEntity findByUserEmail(String userEmail);
}
