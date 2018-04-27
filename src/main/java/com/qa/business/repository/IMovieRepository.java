package com.qa.business.repository;

import com.qa.persistence.domain.Movie;

public interface IMovieRepository {

	String getallmovies();

	Movie getAMovie(Long id);

	Movie createAMovie(Movie movie);

	String updateAMovie(String movie);

	String deleteAMovie(Long id);
}
