package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IMovieRepository;
import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

public class MovieService implements IMovieService {
	
	
	
	private IMovieRepository repo;
	
	@Inject
	private JSONUtil util;

	@Override
	public String getAllMovies() {

		return repo.getallmovies();
	}

	@Override
	public Movie getAMovie(Long id) {

		return repo.getAMovie(id);
	}

	@Override
	public Movie createAMovie(String movie) {
		
		Movie newMovie = util.getObjectForJSON(movie, Movie.class);
		
		return repo.createAMovie(newMovie);
	}

	@Override
	public String updateAMovie(String movie) {
		
		return repo.updateAMovie(movie);
	}

	@Override
	public String deleteAMovie(Long id) {
		
		return repo.deleteAMovie(id);
	}

}
