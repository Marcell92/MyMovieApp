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

	// changed from inject to just private JSONUtil util = new JSONUtil and added
	// startmoviemap() at the
	// beginning of createmovie and also added id++ at the end of the method

	private JSONUtil util = new JSONUtil();

	private Map<Long, Movie> MovieMap;

	public Map<Long, Movie> getMovieMap() {
		return MovieMap;
	}

	public void setMovieMap(Map<Long, Movie> movieMap) {
		MovieMap = movieMap;
	}

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
		// startMovieMap();
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);

		if (getAMovie(newMovie.getId()) != null) {

			MovieMap.put(id, newMovie);

			id++;
			return util.getJSONforObject(newMovie);
		} else {
			return "{\"message\": \"the movie already exists\"}";
		}
	}

	@Override
//	public String updateAMovie(Movie movie) {
//
//		if (movie != null) {
//
//			MovieMap.put(movie.getId(), movie);
//
//			return "{\"message\": \"the movie has been updated\"}";
//		}
//		return "{\"message\": \"the movie could not be updated\"}";
//
//	}
	
	public String updateAMovie (Movie movie) {
		
		if(movie != null) {
			MovieMap.replace(movie.getId(), movie);
			
			return "{\"message\": \"the movie has been updated\"}";
		}
		
		return "{\"message\": \"the movie could not be updated\"}";
	}

	// public String updatingfields(Movie oldmovie, Movie newMovie) {
	//
	// if(oldmovie.getMovieTitle() != null) {
	//
	// oldmovie.setMovieTitle(newMovie.getMovieTitle());
	// }
	//
	// if(oldmovie.getGenre() != null) {
	//
	// oldmovie.setGenre(newMovie.getGenre());
	// }
	//
	// if(oldmovie.getRating() != null) {
	//
	// oldmovie.setRating(newMovie.getRating());
	// }
	//
	// return util.getJSONforObject(oldmovie);
	//
	// }

	@Override
	public String deleteAMovie(Long id) {

		Movie newMovie = util.getObjectForJSON(getAMovie(id), Movie.class);

		if (newMovie != null) {

			MovieMap.remove(id);
			return "{\"message\":\"movie deleted\"}";
		}
		
		return "{\"message\":\"movie can't be deleted\"}";
	}

}
