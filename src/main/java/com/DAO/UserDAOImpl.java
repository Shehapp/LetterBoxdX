package com.DAO;

import com.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{


    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserByUserName(String userName) {

        try {
            return entityManager.createQuery(" from User u where u.userName=:userName", User.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User getUserByUserNameAndPassword(String userName, String password) {
        try {

            return entityManager.createQuery(" from User u where u.userName=:userName and u.password=:password", User.class)
                    .setParameter("userName", userName)
                    .setParameter("password", password)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        try {
            entityManager.createQuery("from User u where u.email=:email")
                    .setParameter("email", email)
                    .getSingleResult();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public boolean isMovieInWatchList(String userName, String imdbID) {
         User user= this.getUserByUserName(userName);

        return user.getWatchlist().contains(entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult());
    }

    @Override
    public boolean isMovieInWatched(String userName, String imdbID) {
        try {
            entityManager.createQuery("from Watched w where w.user.userName=:user and w.movie.imdbId=:movie", Watched.class)
                    .setParameter("user", userName)
                    .setParameter("movie", imdbID)
                    .getSingleResult();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean isMovieInFavorite(String userName, String imdbID) {
        User user= this.getUserByUserName(userName);
        return user.getFavorite().contains(entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult());
    }

    @Override
    public List<Watched> getWatched(String userName, int size) {
        return entityManager.createQuery("from Watched w where w.user.userName=:userName order by w.createdAt ASC ", Watched.class)
                .setParameter("userName", userName)
                .setFirstResult(0).setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Movie> getWatchList(String userName) {
        User user= this.getUserByUserName(userName);
        return new ArrayList<>(user.getWatchlist());
    }

    @Override
    public List<Movie> getFavorite(String userName) {
        User user= this.getUserByUserName(userName);
        return new ArrayList<>(user.getFavorite());

    }




    @Override
    public List<Review> getMovieReviews(String imdbID) {
        return entityManager.createQuery("from Review where movie.imdbId=:imdbId",Review.class).
                setParameter("imdbId",imdbID).getResultList();
    }

    @Override
    public List<Review> getUserReviews(String userName) {
        return entityManager.createQuery("from Review where user.userName=:userName",Review.class).
                setParameter("userName",userName).getResultList();
    }

    @Override
    public Review getReview(String userName, String imdbID) {
        try {
            return entityManager.createQuery("from Review where user.userName=:userName and " +
                            "movie.imdbId=:imdbID", Review.class).
                    setParameter("userName", userName).
                    setParameter("imdbID", imdbID).getSingleResult();
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public void addReview(Review review) {
        entityManager.persist(review);
    }

    @Override
    public Review getReview(Long reviewID) {
        return entityManager.find(Review.class, reviewID);
    }

    @Override
    public void updateReview(Review review) {
        entityManager.merge(review);
    }

    @Override
    public void deleteReview(String userName, String imdbID) {
        Review review = this.getReview(userName, imdbID);

        // sql query to delete all user log reviews for this review
        entityManager.createQuery("delete from UserLogReview where review.id=:reviewID")
                .setParameter("reviewID", review.getId())
                .executeUpdate();

        entityManager.remove(review);
    }

    @Override
    public void addMovieToWatchList(String userName, String imdbID) {
        User user= this.getUserByUserName(userName);
        Movie movie= entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult();
        user.getWatchlist().add(movie);
        entityManager.merge(user);
    }

    @Override
    public void removeMovieFromWatchList(String userName, String imdbID) {

        User user= this.getUserByUserName(userName);
        Movie movie= entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult();
        user.getWatchlist().remove(movie);
        entityManager.merge(user);
    }

    @Override
    public void addMovieToWatched(String userName, String imdbID) {

        Watched watched = new Watched();
        User user= this.getUserByUserName(userName);
        Movie movie= entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult();
        watched.setMovie(movie);
        watched.setUser(user);
        watched.setCreatedAt(LocalDate.now());
        user.getWatched().add(watched);
        entityManager.merge(user);

    }

    @Override
    public void removeMovieFromWatched(String userName, String imdbID) {

        Watched watched= entityManager.createQuery("from Watched w where w.user.userName=:user and w.movie.imdbId=:movie",Watched.class)
                .setParameter("user",userName)
                .setParameter("movie",imdbID)
                .getSingleResult();
        entityManager.remove(watched);
    }

    @Override
    public void addMovieToFavorite(String userName, String imdbID) {

        User user= this.getUserByUserName(userName);
        Movie movie= entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult();
        user.getFavorite().add(movie);
        entityManager.merge(user);

    }

    @Override
    public void removeMovieFromFavorite(String userName, String imdbID) {

        User user= this.getUserByUserName(userName);
        Movie movie= entityManager.createQuery("from Movie m where m.imdbId=:imdbID", Movie.class)
                .setParameter("imdbID", imdbID)
                .getSingleResult();
        user.getFavorite().remove(movie);
        entityManager.merge(user);
    }

    @Override
    public void addLikedReview(String userName, Long reviewID) {
        Review review = entityManager.find(Review.class, reviewID);
        review.getLikedBy().add(this.getUserByUserName(userName));
        entityManager.merge(review);
    }

    @Override
    public void removeLikedReview(String userName, Long reviewID) {
        Review review = entityManager.find(Review.class, reviewID);
        review.getLikedBy().remove(this.getUserByUserName(userName));
        entityManager.merge(review);
    }

    @Override
    public boolean isLikedReview(String userName, Long reviewID) {
        Review review = entityManager.find(Review.class, reviewID);
        return review.getLikedBy().contains(this.getUserByUserName(userName));
    }

    @Override
    public void addLog(UserLog userLog) {
        entityManager.persist(userLog);
    }

    @Override
    public List<UserLog> getUserLogs(String userName) {
        return entityManager.createQuery("from UserLog where user.userName=:userName",UserLog.class)
                .setParameter("userName",userName)
                .getResultList();
    }

}
