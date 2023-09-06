package com.controller;



import com.DTO.MovieDTO;
import com.DTO.QuoteDTO;
import com.DTO.ReviewDTO;
import com.service.MovieAPI;
import com.service.MovieService;
import com.service.Quote;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@Transactional
public class MainController {

    @Autowired
    Quote quote;

    @Autowired
    MovieAPI consumeMovies;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;

    @GetMapping({"/", "/home"})
    String hh(Model model) {
        try {

            QuoteDTO quote = this.quote.getQuote();
            model.addAttribute("quote", quote);
        }catch (Exception e){
            model.addAttribute("quote", new QuoteDTO("No quote", "No author"));
        }
        return "home-page";
    }

    @RequestMapping("/movie")
    String getMovies(@RequestParam(value = "movieName",required = false) String movieName, Model model) {

        try {
            model.addAttribute("movies", consumeMovies.getMovies(movieName));
        }catch (Exception e){
            model.addAttribute("movies", null);

        }
        model.addAttribute("movieName", movieName);
        return "moviesSearch-page";
    }

    @RequestMapping("/movie/{id}")
    String getMovie(@PathVariable("id") String movieImdbId, Model model,
                    @SessionAttribute(value = "userName", required = false) String userName) {


        // if movie is not in database, get it from api and add it to database
        if(!movieService.isMovieExist(movieImdbId)){
            movieService.addMovie(consumeMovies.getMovie(movieImdbId));
        }

        MovieDTO movieDTO = movieService.getMovieDTOById(movieImdbId);
        model.addAttribute("movie", movieDTO);

        List<ReviewDTO> reviewDTO = userService.getMovieReviewsDTO(movieImdbId, userName);
        model.addAttribute("reviews", reviewDTO);


        if(userName != null){
            // watchlist watched favorite
            model.addAttribute("movieState", userService.getMovieState(userName, movieImdbId));
            model.addAttribute("myReview", userService.getReviewDTO(userName, movieImdbId));
        }else {
            model.addAttribute("movieState", null);
            model.addAttribute("myReview", null);
        }
        return "movie-page";
    }

    @GetMapping("review/{id}")
    String getReview(@PathVariable("id") String reviewID, Model model,
                     @SessionAttribute(value = "userName", required = false) String userName) {

        ReviewDTO reviewDTO = userService.getReview(userName, Long.parseLong(reviewID));
        if(reviewDTO == null){
            throw new RuntimeException("Review not found");
        }
        model.addAttribute("review", reviewDTO);
        return "review-page";
    }

}
