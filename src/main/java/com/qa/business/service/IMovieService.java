package com.qa.business.service;

public interface IMovieService {
	
	String getAllMovies();
	String getAMovie(Long id);
	String createAMovie (String movie);
	String updateAMovie (String movie);
	String deleteAMovie (Long id);

}
