package com.qa.business.service;

import com.qa.persistence.domain.Movie;

public interface IMovieService {
	
	String getAllMovies();
	Movie getAMovie(Long id);
	Movie createAMovie (String movie);
	String updateAMovie (String movie);
	String deleteAMovie (Long id);

}
