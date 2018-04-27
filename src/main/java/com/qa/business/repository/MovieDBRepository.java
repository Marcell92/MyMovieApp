package com.qa.business.repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

public class MovieDBRepository implements IMovieRepository {

	private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;


	@Inject
	private JSONUtil util;


	@Override
	public String getallmovies() {
		Query query = manager.createQuery("SELECT m FROM Movie m");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();

		return util.getJSONforObject(movies);
	}

	@Override
	public Movie getAMovie(Long id) {

		Movie aMovie = findMovie(id);

		if (aMovie != null) {

			return aMovie;
		}

		else {

			return null;
		}
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

	@Override
	@Transactional(REQUIRED)
	public Movie createAMovie(Movie movie) {
//		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		
		if(findMovie(movie.getId()) != null) {
			
			return null;
			
		}
		manager.persist(movie);
		
			return getAMovie(movie.getId());

	}

	@Override
	@Transactional(REQUIRED)
	public String updateAMovie(String movie) {
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		if (movie != null) {

			manager.merge(newMovie);
			return "{\"message\":\"movie updated\"}";
		}

		else {

			return "{\"message\":\"movie can't be updated\"}";
		}
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAMovie(Long id) {

		Movie movieTodelete = findMovie(id);
		manager.remove(movieTodelete);
		return "{\"message\":\"movie was deleted\"}";
	}
	
	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public JSONUtil getUtil() {
		return util;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
