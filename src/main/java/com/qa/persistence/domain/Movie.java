package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
	
	
	@Id
	@GeneratedValue
	private Long id;
	private String movieTitle;
	private String genre;
	private String rating;

	
	public Movie() {
	}
	
	public Movie(String movieTitle, String genre, String rating) {
		
		this.movieTitle = movieTitle;
		this.genre = genre;
		this.rating = rating;
	}
	
	public Long getId() {
		return id;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
