package com.service;

import com.DTO.*;
import com.model.*;

import java.util.List;

public interface UserService {
    User getUserByUserNameAndPassword(String userName, String password);
    void addUser(UserDTO user);
    boolean isUserExist(String userName);
    boolean isEmailExist(String email);
    User convertUserDTOToUser(UserDTO userDTO);
    User getUserByUserName(String userName);


    boolean isMovieInWatchList(String userName, String imdbId);
    boolean isMovieInWatched(String userName, String imdbId);
    boolean isMovieInFavorite(String userName, String imdbId);

    List<Watched> getWatched(String userName,int size);
    List<WatchedDTO> getWatchedDTO(String userName, int size);
    List<Movie> getWatchList(String userName);
    List<MoviePreviewDTO> getWatchListDTO(String userName);

    List<Movie> getFavorite(String userName);
    List<MoviePreviewDTO> getFavoriteDTO(String userName);

    WatchedDTO convertWatchedToWatchedDTO(Watched watched);
    MoviePreviewDTO convertMovieToMoviePreviewDTO(Movie movie);

    void addMovieToWatchList(String userName, String imdbId);
    void removeMovieFromWatchList(String userName, String imdbId);
    void addMovieToWatched(String userName, String imdbId);
    void removeMovieFromWatched(String userName, String imdbId);
    void addMovieToFavorite(String userName, String imdbId);
    void removeMovieFromFavorite(String userName, String imdbId);



    List<Review> getMovieReviews(String imdbID);
    List<ReviewDTO> getMovieReviewsDTO(String imdbID, String userName);
    List<Review> getUserReviews(String userName);
    List<ReviewDTO> getUserReviewsDTO(String name, String userName);
    Review getReview(String userName, String imdbID);
    ReviewDTO getReviewDTO(String userName, String imdbID);
    ReviewDTO getReview(String user,Long reviewID);
    void addReview(ReviewDTO reviewDTO);
    void updateReview(ReviewDTO reviewDTO);
    void deleteReview(String userName, String imdbID);
    ReviewDTO convertReviewToReviewDTO(Review review, String userName);


    void addLikedReview(String userName, Long reviewID);
    boolean isLikedReview(String userName, Long reviewID);


    MovieStateDTO getMovieState(String userName, String imdbID);

    ProfileDTO getProfileDTO(String name, String userName);


    void addLogMovie(String userName, String imdbID, String description);
    void addLogReview(String userName, Long reviewId, String description);
    List<UserLog> getUserLogs(String userName);
    List<UserLogDTO> getUserLogsDTO(String userName);
    UserLogDTO convertUserLogToUserLogDTO(UserLog userLog);
}
