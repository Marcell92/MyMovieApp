package com.qa.business.repository;

public interface IMovieRepository {
	
	String getallmovies();
	String getAMovie(Long id);
	String createAMovie (String movie);
}
