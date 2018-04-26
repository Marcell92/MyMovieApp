package com.qa.business.repository;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

public class MovieDBRepository implements IMovieRepository {
	
	
	private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	@Override
	public String getallmovies() {
		LOGGER.info("MovieDBRepository getAllMovies");
		Query query = manager.createQuery("SELECT m FROM Movie m");
		Collection <Movie> movies = (Collection<Movie>) query.getResultList();
		
		return util.getJSONforObject(movies);
	}

	@Override
	public String getAMovie(Long id) {

		Movie aMovie = findMovie(id);
		
		if (aMovie != null) {
			
			return util.getJSONforObject(aMovie);
		}
		
		else {
			
			return "{\"message\":\"movie not found\"}";
		}
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class,  id);
	}

}