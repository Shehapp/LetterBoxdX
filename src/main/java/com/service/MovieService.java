package com.service;

import com.DAO.MovieDAO;
import com.DTO.MovieDTO;
import com.DTO.MovieStateDTO;
import com.model.Movie;

public interface MovieService {

    void addMovie(MovieDTO movieDTO);
    Movie getMovieById(String imdbId);
    MovieDTO getMovieDTOById(String imdbId);
    boolean isMovieExist(String imdbId);
    Movie convertMovieDTOToMovie(MovieDTO movieDTO);
    MovieDTO convertMovieToMovieDTO(Movie movie);

}
