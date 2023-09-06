package com.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDTO {
    Long reviewId;
    String review;
    Double rating;
    String imdbID;
    LocalDate createdAt;
    String userName;
    String userImage;
    Long likes;
    boolean isLikedByUser;

    String movieTitle;
    String moviePoster;
    String movieYear;
    
}
