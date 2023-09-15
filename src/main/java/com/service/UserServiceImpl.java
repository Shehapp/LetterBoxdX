package com.service;

import com.DAO.UserDAO;
import com.DTO.*;
import com.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
/*
* convertMethods has implemented by:
* @shahpp
* @4aiGpt
* */
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    MovieService movieService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=this.getUserByUserName(s);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }






    public User getUserByUserNameAndPassword(String userName, String password) {
        return userDAO.getUserByUserNameAndPassword(userName, password);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user=this.convertUserDTOToUser(userDTO);
        userDAO.addUser(user);
    }

    @Override
    public boolean isUserExist(String userName) {
            return this.getUserByUserName(userName)!=null;
    }

    @Override
    public boolean isEmailExist(String email) {
        return userDAO.isEmailExist(email);
    }


    @Override
    public User convertUserDTOToUser(UserDTO userDTO){
        User user=new User();
        user.setUserName(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        user.setPhotoId(new Random().nextInt(10)+".png");
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDAO.getUserByUserName(userName);
    }



    @Override
    public boolean isMovieInWatchList(String userName, String imdbId) {

        return userDAO.isMovieInWatchList(userName, imdbId);
    }

    @Override
    public boolean isMovieInWatched(String userName, String imdbId) {
        return  userDAO.isMovieInWatched(userName, imdbId);
    }

    @Override
    public boolean isMovieInFavorite(String userName, String imdbId) {
        return  userDAO.isMovieInFavorite(userName, imdbId);
    }


    @Override
    public List<Watched> getWatched(String userName,int size) {
        return userDAO.getWatched(userName,size);
    }

    @Override
    public List<WatchedDTO> getWatchedDTO(String userName, int size) {
        List<WatchedDTO> watchedDTOS=new ArrayList<>();
        for (Watched w : this.getWatched(userName, size)) {
            watchedDTOS.add(this.convertWatchedToWatchedDTO(w));
        }
        return watchedDTOS;
    }


    @Override
    public List<Movie> getWatchList(String userName) {
        return userDAO.getWatchList(userName);
    }

    @Override
    public List<MoviePreviewDTO> getWatchListDTO(String userName) {
        List<MoviePreviewDTO> moviePreviewDTOS=new ArrayList<>();
        for (Movie m : this.getWatchList(userName)) {
            moviePreviewDTOS.add(this.convertMovieToMoviePreviewDTO(m));
        }
        return moviePreviewDTOS;
    }

    @Override
    public List<Movie> getFavorite(String userName) {
        return userDAO.getFavorite(userName);
    }

    @Override
    public List<MoviePreviewDTO> getFavoriteDTO(String userName) {
        List<MoviePreviewDTO> moviePreviewDTOS=new ArrayList<>();
        for (Movie m : this.getFavorite(userName)) {
            moviePreviewDTOS.add(this.convertMovieToMoviePreviewDTO(m));
        }
        return moviePreviewDTOS;
    }

    @Override
    public WatchedDTO convertWatchedToWatchedDTO(Watched w) {

            WatchedDTO watchedDTO=new WatchedDTO();

            watchedDTO.setImdbID(w.getMovie().getImdbId());
            watchedDTO.setCreatedAt(w.getCreatedAt().toLocalDateTime().toLocalDate());
            watchedDTO.setPoster(w.getMovie().getPoster());
            watchedDTO.setTitle(w.getMovie().getTitle());
            watchedDTO.setYear(w.getMovie().getYear());

        return watchedDTO;
    }

    @Override
    public MoviePreviewDTO convertMovieToMoviePreviewDTO(Movie movie) {
        MoviePreviewDTO moviePreviewDTO=new MoviePreviewDTO();
        moviePreviewDTO.setImdbID(movie.getImdbId());
        moviePreviewDTO.setPoster(movie.getPoster());
        moviePreviewDTO.setTitle(movie.getTitle());
        moviePreviewDTO.setYear(movie.getYear());
        return moviePreviewDTO;
    }


    @Override
    public void addMovieToWatchList(String userName, String imdbId) {
        userDAO.addMovieToWatchList(userName, imdbId);
    }

    @Override
    public void removeMovieFromWatchList(String userName, String imdbId) {
        userDAO.removeMovieFromWatchList(userName, imdbId);
    }

    @Override
    public void addMovieToWatched(String userName, String imdbId) {
        userDAO.addMovieToWatched(userName, imdbId);
    }

    @Override
    public void removeMovieFromWatched(String userName, String imdbId) {
        userDAO.removeMovieFromWatched(userName, imdbId);
    }

    @Override
    public void addMovieToFavorite(String userName, String imdbId) {
        userDAO.addMovieToFavorite(userName, imdbId);
    }

    @Override
    public void removeMovieFromFavorite(String userName, String imdbId) {
        userDAO.removeMovieFromFavorite(userName, imdbId);
    }





    @Override
    public List<Review> getMovieReviews(String imdbID) {
        return userDAO.getMovieReviews(imdbID);
    }

    @Override
    public List<ReviewDTO> getMovieReviewsDTO(String imdbID, String userName) {
        List<ReviewDTO> reviewDTOS=new ArrayList<>();
        for (Review r : this.getMovieReviews(imdbID)) {
            if(userName==null || !userName.equals(r.getUser().getUserName()))
                reviewDTOS.add(this.convertReviewToReviewDTO(r,userName));
        }
        return reviewDTOS;
    }


    @Override
    public List<Review> getUserReviews(String userName) {
        return userDAO.getUserReviews(userName);
    }

    @Override
    public List<ReviewDTO> getUserReviewsDTO(String name, String userName) {
        List<ReviewDTO> reviewDTOS=new ArrayList<>();
        for (Review r : this.getUserReviews(name)) {
            reviewDTOS.add(this.convertReviewToReviewDTO(r,userName));
        }
        return reviewDTOS;
    }


    @Override
    public ReviewDTO convertReviewToReviewDTO(Review review, String userName) {
        if(review==null)
            return null;
        ReviewDTO reviewDTO=new ReviewDTO();
        reviewDTO.setReviewId(review.getId());
        reviewDTO.setReview(review.getMyReview());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setImdbID(review.getMovie().getImdbId());
        reviewDTO.setCreatedAt(review.getCreatedAt().toLocalDateTime().toLocalDate());
        reviewDTO.setUserName(review.getUser().getUserName());
        reviewDTO.setUserImage(review.getUser().getPhotoId());
        reviewDTO.setLikes((long) review.getLikedBy().size());
        reviewDTO.setMovieTitle(review.getMovie().getTitle());
        reviewDTO.setMoviePoster(review.getMovie().getPoster());
        reviewDTO.setMovieYear(review.getMovie().getYear());
        if(userName==null)
            reviewDTO.setLikedByUser(false);
        else
            reviewDTO.setLikedByUser(userDAO.isLikedReview(userName,review.getId()));

        return reviewDTO;
    }


    @Override
    public Review getReview(String userName, String imdbID) {
        return userDAO.getReview(userName, imdbID);
    }

    @Override
    public ReviewDTO getReviewDTO(String userName, String imdbID) {
        return this.convertReviewToReviewDTO(this.getReview(userName, imdbID),userName);
    }

    @Override
    public ReviewDTO getReview(String user,Long reviewID) {
        return convertReviewToReviewDTO(userDAO.getReview(reviewID),user);
    }

    @Override
    public void addReview(ReviewDTO reviewDTO) {
        Review review=new Review();
        review.setMyReview(reviewDTO.getReview());
        review.setRating(reviewDTO.getRating());
        review.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));

        review.setUser(this.getUserByUserName(reviewDTO.getUserName()));
        review.setMovie(movieService.getMovieById(reviewDTO.getImdbID()));
        userDAO.addReview(review);
        reviewDTO.setReviewId(review.getId());
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {
        Review review=this.getReview(reviewDTO.getUserName(),reviewDTO.getImdbID());

        review.setMyReview(reviewDTO.getReview());
        review.setRating(reviewDTO.getRating());
        review.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));

        userDAO.updateReview(review);
        reviewDTO.setReviewId(review.getId());
    }

    @Override
    public void deleteReview(String userName, String imdbID) {
        userDAO.deleteReview(userName, imdbID);
    }


    @Override
    public void addLikedReview(String userName, Long reviewID) {
            userDAO.addLikedReview(userName, reviewID);
    }

    @Override
    public void removeLikedReview(String userName, Long reviewID) {
        userDAO.removeLikedReview(userName, reviewID);
    }


    @Override
    public boolean isLikedReview(String userName, Long reviewID) {
        return userDAO.isLikedReview(userName, reviewID);
    }




    @Override
    public MovieStateDTO getMovieState(String userName, String imdbId) {
        MovieStateDTO movieStateDTO=new MovieStateDTO();
        movieStateDTO.setWatched(this.isMovieInWatched(userName, imdbId));
        movieStateDTO.setWatchList(this.isMovieInWatchList(userName, imdbId));
        movieStateDTO.setFavorite(this.isMovieInFavorite(userName, imdbId));
        return movieStateDTO;
    }

    @Override
    public ProfileDTO getProfileDTO(String name, String userName) {
        User user=this.getUserByUserName(name);

        ProfileDTO profileDTO=new ProfileDTO();
        profileDTO.setName(name);
        profileDTO.setCreatedAt(user.getCreatedAt().toLocalDateTime().toLocalDate());
        profileDTO.setPoster(user.getPhotoId());
        profileDTO.setMyProfile(name.equals(userName));
        return profileDTO;
    }

    @Override
    public void addLogMovie(String userName, String imdbID, String description) {
        UserLogMovie userLogMovie=new UserLogMovie();
        userLogMovie.setUser(this.getUserByUserName(userName));
        userLogMovie.setMovie(movieService.getMovieById(imdbID));
        userLogMovie.setDescription(description);
        userLogMovie.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        userDAO.addLog(userLogMovie);
    }

    @Override
    public void addLogReview(String userName, Long reviewId, String description) {
        UserLogReview userLogReview=new UserLogReview();
        userLogReview.setUser(this.getUserByUserName(userName));
        userLogReview.setReview(userDAO.getReview(reviewId));
        userLogReview.setDescription(description);
        userLogReview.setCreatedAt(new Timestamp(Calendar.getInstance().getTime().getTime()));
        userDAO.addLog(userLogReview);
    }

    @Override
    public List<UserLog> getUserLogs(String userName) {
        return userDAO.getUserLogs(userName);
    }

    @Override
    public List<UserLogDTO> getUserLogsDTO(String userName) {
        List<UserLogDTO> userLogList=new ArrayList<>();
        List<UserLog> userLogs=this.getUserLogs(userName);
        if(userLogs==null)
            return null;
        for(UserLog userLog:userLogs){
            userLogList.add(convertUserLogToUserLogDTO(userLog));
        }
        return userLogList;
    }

    @Override
    public UserLogDTO convertUserLogToUserLogDTO(UserLog userLog) {
        UserLogDTO userLogDTO=new UserLogDTO();
        userLogDTO.setUserName(userLog.getUser().getUserName());
        userLogDTO.setDescription(userLog.getDescription());
        userLogDTO.setCreatedAt(userLog.getCreatedAt().toLocalDateTime().toLocalDate());
        if(userLog instanceof UserLogMovie){
            userLogDTO.setMovieName(((UserLogMovie) userLog).getMovie().getTitle());
            userLogDTO.setId(((UserLogMovie) userLog).getMovie().getImdbId());
        }else{
            userLogDTO.setId(((UserLogReview) userLog).getReview().getId().toString());
        }
        return userLogDTO;
    }


}
