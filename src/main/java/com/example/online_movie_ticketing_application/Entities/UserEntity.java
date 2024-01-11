package com.example.online_movie_ticketing_application.Entities;

import com.example.online_movie_ticketing_application.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private int age;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(unique = true, nullable = false)
    private String mobNo;

    private String address;

    private String password;

    private Role role;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TicketEntity> ticketEntityList = new ArrayList<>();
}
