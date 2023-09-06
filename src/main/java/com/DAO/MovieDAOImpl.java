package com.DAO;

import com.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class MovieDAOImpl implements MovieDAO {



    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie getMovieByImdbID(String imdbID) {

        try {
            return entityManager.createQuery(" from Movie where imdbId=:imdbId", Movie.class)
                    .setParameter("imdbId", imdbID)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }



     @Override
    public Director getDirectorByName(String name) {
        try {
            return entityManager.createQuery(" from Director d where d.name=:name", Director.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Actor getActorByName(String name) {
        try {
            return entityManager.createQuery(" from Actor a where a.name=:name", Actor.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }


}
