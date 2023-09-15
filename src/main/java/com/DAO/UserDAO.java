package com.DAO;

import com.model.*;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    User getUserByUserName(String userName);
    User getUserByUserNameAndPassword(String userName, String password);


    boolean isEmailExist(String email);

    boolean isMovieInWatchList(String userName, String imdbID);
    boolean isMovieInWatched(String userName, String imdbID);
    boolean isMovieInFavorite(String userName, String imdbID);

    List<Watched> getWatched(String userName, int page);
    List<Movie> getWatchList(String userName);
    List<Movie> getFavorite(String userName);
    List<Review> getMovieReviews(String imdbID);
    List<Review> getUserReviews(String userName);
    Review getReview(String userName, String imdbID);
    void addReview(Review review);
    Review getReview(Long reviewID);
    void updateReview(Review review);
    void deleteReview(String userName, String imdbID);

    void addMovieToWatchList(String userName, String imdbID);
    void removeMovieFromWatchList(String userName, String imdbID);
    void addMovieToWatched(String userName, String imdbID);
    void removeMovieFromWatched(String userName, String imdbID);
    void addMovieToFavorite(String userName, String imdbID);
    void removeMovieFromFavorite(String userName, String imdbID);


    void addLikedReview(String userName, Long reviewID);
    void removeLikedReview(String userName, Long reviewID);
    boolean isLikedReview(String userName, Long reviewID);



    void addLog(UserLog userLog);
    List<UserLog> getUserLogs(String userName);

    void addRole(Role role);
    Role getRoleByName(String name);

}
