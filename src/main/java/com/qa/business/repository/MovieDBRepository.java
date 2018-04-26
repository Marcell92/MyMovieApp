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
		LOGGER.info("MovieDBRepository getAllMovies");
		Query query = manager.createQuery("SELECT m FROM Movie m");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();

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
		return manager.find(Movie.class, id);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAMovie(String movie) {
		LOGGER.info("MovieDBRepository createAMovie");
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		manager.persist(newMovie);
		return "{\"message\":\"movie added\"}";

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

}
