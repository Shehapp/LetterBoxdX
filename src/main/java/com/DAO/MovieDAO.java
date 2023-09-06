package com.DAO;

import com.model.Actor;
import com.model.Director;
import com.model.Movie;
import com.model.User;

public interface MovieDAO {


    void addMovie(Movie movie);
    Movie getMovieByImdbID(String imdbID);

    Director getDirectorByName(String name);
    Actor getActorByName(String name);




}
