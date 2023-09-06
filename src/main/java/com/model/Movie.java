package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Setter
@Getter
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String imdbId;
    private String title;
    private String year;
    private String plot;
    private String poster;
    private String imdbRating;
    private String runtime;
    private String released;

    @JoinColumn(name = "movie_id")
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Review> reviews;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private List<Director> directors;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

}
