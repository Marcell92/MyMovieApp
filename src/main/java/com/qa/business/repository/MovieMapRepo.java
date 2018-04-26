package com.qa.business.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class MovieMapRepo implements IMovieRepository {

	@Inject
	private JSONUtil util;

	private Map<Long, Movie> MovieMap;
	private Long id = 1L;

	public MovieMapRepo() {

		this.MovieMap = new HashMap<Long, Movie>();
		startMovieMap();
	}

	private void startMovieMap() {
		Movie newMovie = new Movie("Chad's Life", "Horror", "18");
		MovieMap.put(1L, newMovie);
	}

	@Override
	public String getallmovies() {

		return util.getJSONforObject(MovieMap.values());
	}

	@Override
	public String getAMovie(Long id) {

		return util.getJSONforObject(MovieMap.get(id));
	}

	@Override
	public String createAMovie(String movie) {
		id++;
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		MovieMap.put(id, newMovie);
		return util.getJSONforObject(newMovie);
	}

	@Override
	public String updateAMovie(String movie) {

		Movie newMovie = util.getObjectForJSON(movie, Movie.class);

		if (movie != null) {

			MovieMap.put(id, newMovie);
			return "{\"message\":\"movie updated\"}";
		}

		else {

			return "{\"message\":\"movie can't be updated\"}";
		}
	}

	@Override
	public String deleteAMovie(Long id) {

		MovieMap.remove(id);
		return "{\"message\":\"movie deleted\"}";
	}

}
