package com.service;

import com.DTO.MovieDTO;
import com.DTO.MoviePreviewDTO;

import java.util.List;

public interface MovieAPI {

    List<MoviePreviewDTO> getMovies(String movieName);
    MovieDTO getMovie(String movieId);
    String validateSearchString(String movieId);
}
