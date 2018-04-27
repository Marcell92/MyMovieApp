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
//	private Movie Hulk = new Movie ("Hulk", "action", "15");
	private JSONUtil util = new JSONUtil();

	@Test
	public void testCreateAndDeleteAMovie() {
		
		String movie = util.getJSONforObject(IronMan);
//		String movie1 = util.getJSONforObject(Hulk);
		
		repo.createAMovie(movie);
		assertEquals(repo.getMovieMap().size(), 1);
		
		repo.getAMovie(1L);
		assertEquals(repo.getAMovie(1L), movie);
		
		repo.updateAMovie(movie);
		assertEquals(repo.updateAMovie(movie),"{\"message\":\"movie updated\"}");
		
		repo.deleteAMovie(1L);
		assertEquals(repo.getMovieMap().size(), 0);
		
	}
	
//	@Test
//	public void testupdateAMovie() {
//		
//		String movie = util.getJSONforObject(IronMan);
//		repo.createAMovie(movie);
//		
//		
//		
//	}
//		

}
