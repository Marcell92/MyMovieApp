package com.qa.repotests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;
import com.qa.business.repository.MovieMapRepo;

public class MapRepoTest {
	
	private MovieMapRepo repo = new MovieMapRepo();
	private Movie IronMan = new Movie("Iron Man", "action", "15");
	private JSONUtil util = new JSONUtil();

	@Test
	public void testcreateAMovie() {
		
		String movie = util.getJSONforObject(IronMan);
		repo.createAMovie(movie);
		assertEquals(repo.getMovieMap().size(), 1);
		
	}
		

}
