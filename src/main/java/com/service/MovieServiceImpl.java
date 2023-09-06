package com.service;

import com.DAO.MovieDAO;
import com.DTO.MovieDTO;
import com.model.Actor;
import com.model.Director;
import com.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieDAO movieDAO;

    @Override
    public void addMovie(MovieDTO movie) {
        Movie m = this.convertMovieDTOToMovie(movie);
        movieDAO.addMovie(m);
    }

    @Override
    public Movie getMovieById(String imdbId) {
        return movieDAO.getMovieByImdbID(imdbId);
    }

    @Override
    public MovieDTO getMovieDTOById(String imdbId) {
        Movie movie = this.getMovieById(imdbId);
        if(movie == null){
            return null;
        }
        return this.convertMovieToMovieDTO(movie);
    }

    @Override
    public boolean isMovieExist(String imdbId) {
        return this.getMovieById(imdbId) != null;
    }

    @Override
    public Movie convertMovieDTOToMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setImdbId(movieDTO.getImdbID());
        movie.setTitle(movieDTO.getTitle());
        movie.setYear(movieDTO.getYear());
        movie.setImdbRating(movieDTO.getImdbRating());
        movie.setReleased(movieDTO.getReleased());
        movie.setRuntime(movieDTO.getRuntime());

        String []directors = movieDTO.getDirectors().split(",");
        List<Director> directorList = new ArrayList<>();
        for (String d : directors) {
            d= d.trim();
            Director director1 = movieDAO.getDirectorByName(d);
            if(director1 != null){
                directorList.add(director1);
                continue;
            }
            director1 = new Director();
            director1.setName(d);
            directorList.add(director1);
        }

        String []actors = movieDTO.getActors().split(",");
        List<Actor> actorsList = new ArrayList<>();
        for (String a : actors) {
            a = a.trim();

            Actor actor = movieDAO.getActorByName(a);
            if (actor != null) {
                actorsList.add(actor);
                continue;
            }
            actor = new Actor();
            actor.setName(a);
            actorsList.add(actor);
        }

        movie.setActors(actorsList);
        movie.setDirectors(directorList);
        movie.setPlot(movieDTO.getPlot());
        movie.setPoster(movieDTO.getPoster());
        return movie;

    }

    @Override
    public MovieDTO convertMovieToMovieDTO(Movie movie) {
        MovieDTO movieDTO=new MovieDTO();
        movieDTO.setImdbID(movie.getImdbId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setYear(movie.getYear());
        movieDTO.setPlot(movie.getPlot());
        movieDTO.setPoster(movie.getPoster());
        movieDTO.setImdbRating(movie.getImdbRating());
        movieDTO.setReleased(movie.getReleased());
        movieDTO.setRuntime(movie.getRuntime());
        StringBuilder directors = new StringBuilder();
        for (Director d : movie.getDirectors()) {
            directors.append(d.getName()).append(", ");
        }
        directors.deleteCharAt(directors.length()-1);
        directors.deleteCharAt(directors.length()-1);
        movieDTO.setDirectors(directors.toString());

        StringBuilder actors = new StringBuilder();
        for (Actor a : movie.getActors()) {
            actors.append(a.getName()).append(", ");
        }
        actors.deleteCharAt(actors.length()-1);
        actors.deleteCharAt(actors.length()-1);
        movieDTO.setActors(actors.toString());
        return movieDTO;

    }


}
