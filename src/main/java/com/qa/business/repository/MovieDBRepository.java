package com.qa.business.repository;

import java.util.Collection;

import javax.enterprise.inject.Default;
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


@Transactional(SUPPORTS)
@Default
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
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		
		if(findMovie(newMovie.getId()) != null) {
			
			return "{\"message\":\"movie already exists\"}";
			
		}
		manager.persist(newMovie);
		
			return "{\"message\":\"movie added\"}";

	}

	@Override
	@Transactional(REQUIRED)
	public String updateAMovie(Movie movie) {
		
		if (movie != null) {
			
			manager.merge(movie);
			
			return "{\"message\": \"the movie has been updated\"}";
		}
		return "{\"message\": \"the movie couldn't be updated\"}";
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
