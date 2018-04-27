package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IMovieRepository;
import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

public class MovieService implements IMovieService {
	
	
	@Inject
	private IMovieRepository repo;
	
	@Inject
	private JSONUtil util;

	@Override
	public String getAllMovies() {

		return repo.getallmovies();
	}

	@Override
	public String getAMovie(Long id) {

		return repo.getAMovie(id);
	}

	@Override
	public String createAMovie(String movie) {
		
		return repo.createAMovie(movie);
	}

	@Override
	public String updateAMovie(String movie) {
		
		Movie movieA = util.getObjectForJSON(movie, Movie.class);
		
		return repo.updateAMovie(movieA);
	}

	@Override
	public String deleteAMovie(Long id) {
		
		return repo.deleteAMovie(id);
	}

}
