package com.controller;

import com.DTO.ReviewDTO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/movie/{id}")
public class MovieController {

    @Autowired
    UserService userService;

    @PostMapping("/fav/{action}")
    public String fav(@PathVariable("id") String movieId,
                      @PathVariable("action") boolean action,
                      Authentication authentication) {
        System.out.println("iam heeeeeeeeeeeeerrrrrrrrrrrrrrrrr");
        String userName=authentication!=null?authentication.getName():null;
        if (action) {
            userService.addMovieToFavorite(userName, movieId);
        } else {
            userService.removeMovieFromFavorite(userName, movieId);
        }
        return "redirect:/movie/" + movieId;
    }


    @PostMapping("/watch/{action}")
    public String watchMovie(@PathVariable("id") String movieId,
                             @PathVariable("action") boolean action,
                             Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        if (action) {
            userService.addMovieToWatched(userName, movieId);
        } else {
            userService.removeMovieFromWatched(userName, movieId);
        }
        return "redirect:/movie/" + movieId;
    }

    @PostMapping("/watchlist/{action}")
    public String watchlistMovie(@PathVariable("id") String movieId,
                                 @PathVariable("action") boolean action,
                                 Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;

        if (action) {
            userService.addMovieToWatchList(userName, movieId);
        } else {
            userService.removeMovieFromWatchList(userName, movieId);
        }
        return "redirect:/movie/" + movieId;
    }


    @PostMapping("/add-review")
    public String addReview(@PathVariable("id") String movieId,
                            ReviewDTO reviewDTO,
                            Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        reviewDTO.setUserName(userName);
        reviewDTO.setImdbID(movieId);

        if (userService.getReview(userName, movieId) == null) {
            userService.addReview(reviewDTO);

        } else {
            userService.updateReview(reviewDTO);
        }
        return "redirect:/movie/" + movieId;
    }


    @PostMapping("/delete-review")
    public String deleteReview(@PathVariable("id") String movieId,
                               Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        userService.deleteReview(userName, movieId);
        return "redirect:/movie/" + movieId;
    }


    @PostMapping("/like-review/{reviewId}")
    public String likeReview(@PathVariable("reviewId") Long reviewId,
                             Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        userService.addLikedReview(userName, reviewId);

        return "redirect:/review/" + reviewId;
    }
    @PostMapping("/unlike-review/{reviewId}")
    public String unlikeReview(@PathVariable("reviewId") Long reviewId,
                               Authentication authentication) {
        String userName=authentication!=null?authentication.getName():null;
        userService.removeLikedReview(userName, reviewId);

        return "redirect:/review/" + reviewId;
    }
}

