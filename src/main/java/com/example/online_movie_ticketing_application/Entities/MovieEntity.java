package com.example.online_movie_ticketing_application.Entities;

import com.example.online_movie_ticketing_application.Enums.Genre;
import com.example.online_movie_ticketing_application.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String movieName;

    private double ratings;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movieEntity", cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList = new ArrayList<>();

}
