package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "reviews")
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String myReview;
    private Double rating;
    private Timestamp createdAt;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "movie_id")
    @ManyToOne
    private Movie movie;

    @JoinTable(
            name = "review_liked_by_users",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> likedBy;

}
