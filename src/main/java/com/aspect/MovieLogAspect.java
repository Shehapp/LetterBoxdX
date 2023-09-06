package com.aspect;

import com.DTO.ReviewDTO;
import com.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MovieLogAspect {

    @Autowired
    UserService userService;

    @Before("execution(* com.service.UserServiceImpl.addMovieToFavorite(..))")
    public void logMovie(JoinPoint joinPoint) {
        String userName = (String) joinPoint.getArgs()[0];
        String imdbId = (String) joinPoint.getArgs()[1];
        userService.addLogMovie(userName, imdbId, "add to favorite");
    }

    @Before("execution(* com.service.UserServiceImpl.removeMovieFromFavorite(..))")
    public void logMovie2(JoinPoint joinPoint) {
        String userName = (String) joinPoint.getArgs()[0];
        String imdbId = (String) joinPoint.getArgs()[1];
        userService.addLogMovie(userName, imdbId, "remove from favorite");
    }

    @Before("execution(* com.service.UserServiceImpl.addMovieToWatched(..))")
    public void logMovie3(JoinPoint joinPoint) {
        System.out.println("logMovie3");
        String userName = (String) joinPoint.getArgs()[0];
        String imdbId = (String) joinPoint.getArgs()[1];
        userService.addLogMovie(userName, imdbId, "add to watched");
    }

    @Before("execution(* com.service.UserServiceImpl.removeMovieFromWatched(..))")
    public void logMovie4(JoinPoint joinPoint) {
        String userName = (String) joinPoint.getArgs()[0];
        String imdbId = (String) joinPoint.getArgs()[1];
        userService.addLogMovie(userName, imdbId, "remove from watched");
    }

    @Before("execution(* com.service.UserServiceImpl.addMovieToWatchList(..))")
    public void logMovie5(JoinPoint joinPoint) {
        String userName = (String) joinPoint.getArgs()[0];
        String imdbId = (String) joinPoint.getArgs()[1];
        userService.addLogMovie(userName, imdbId, "add to watchlist");
    }

    @Before("execution(* com.service.UserServiceImpl.removeMovieFromWatchList(..))")
    public void logMovie6(JoinPoint joinPoint) {
        String userName = (String) joinPoint.getArgs()[0];
        String imdbId = (String) joinPoint.getArgs()[1];
        userService.addLogMovie(userName, imdbId, "remove from watchlist");
    }

    @After("execution(* com.service.UserServiceImpl.addReview(..))")
    public void logMovie7(JoinPoint joinPoint) {
        ReviewDTO reviewDTO = (ReviewDTO) joinPoint.getArgs()[0];
        userService.addLogReview(reviewDTO.getUserName(), reviewDTO.getReviewId(), "add review");
    }

    @After("execution(* com.service.UserServiceImpl.updateReview(..))")
    public void logMovie8(JoinPoint joinPoint) {
        ReviewDTO reviewDTO = (ReviewDTO) joinPoint.getArgs()[0];
        userService.addLogReview(reviewDTO.getUserName(), reviewDTO.getReviewId(), "update review");
    }


    @Before("execution(* com.service.UserServiceImpl.addLikedReview(..))")
    public void logMovie10(JoinPoint joinPoint) {
        String userName = (String) joinPoint.getArgs()[0];
        Long reviewID = (Long) joinPoint.getArgs()[1];
        if(userService.isLikedReview(userName, reviewID))
            userService.addLogReview(userName, reviewID, "unlike review");
        else
            userService.addLogReview(userName, reviewID, "like review");
    }



}
