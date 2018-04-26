package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IMovieRepository;

public class MovieService implements IMovieService {
	
	
	@Inject
	private IMovieRepository repo;

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
		
		return repo.updateAMovie(movie);
	}

	@Override
	public String deleteAMovie(Long id) {
		
		return repo.deleteAMovie(id);
	}

}
