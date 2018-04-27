package com.qa.business.repository;

import com.qa.persistence.domain.Movie;

public interface IMovieRepository {

	String getallmovies();

	String getAMovie(Long id);

	String createAMovie(String movie);

	String updateAMovie(Movie movie);

	String deleteAMovie(Long id);
}
