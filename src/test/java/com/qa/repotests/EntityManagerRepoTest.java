package com.qa.repotests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.repository.MovieDBRepository;
import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class EntityManagerRepoTest {

	@InjectMocks
	private MovieDBRepository movierepo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;
	
	@Mock
	private Movie movie;
	
	@Inject
	private JSONUtil util;

	private static final String mockList = "[{\"movieTitle\":\"Life of Nabeel\",\"genre\":\"horror\",\"rating\":\"18\"}]";
	private static final String mockObject = "{\"movieTitle\":\"Life of Nabeel\",\"genre\":\"horror\",\"rating\":\"18\"}";

	@Before
	public void pre() {

		movierepo.setManager(manager);
		util = new JSONUtil();
		movierepo.setUtil(util);
	}

	@Test
	public void testgetallmovies() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("Life of Nabeel", "horror", "18"));
		Mockito.when(query.getResultList()).thenReturn(movies);
		assertEquals(mockList, movierepo.getallmovies());
	}
	
	@Test
	public void testcreateAMovie() {
		Mockito.when(manager.find(Mockito.eq(Movie.class), Mockito.anyString())).thenReturn(null); //works without mockito.when
		String expectedAnswer = movierepo.createAMovie(mockObject);
		String actual = "{\"message\":\"movie added\"}";
		assertEquals(expectedAnswer, actual);
	}
	
	@Test
	public void testupdateAMovie() {
		String expectedAnswer = movierepo.updateAMovie(mockObject);
		String actual = "{\"message\":\"movie updated\"}";
		assertEquals(expectedAnswer, actual);
	}
	
//	@Test
//	public void testupdateAMovieFail() {
//		String expectedAnswer = movierepo.updateAMovie(null);
//		String actual = "{\"message\":\"movie can't be updated\"}";
//		assertEquals(expectedAnswer, actual);
//	}
	
	@Test
	public void testdeleteAMovie() {
		String expectedAnswer = movierepo.deleteAMovie(1L);
		String actual = "{\"message\":\"movie was deleted\"}";
		assertEquals(expectedAnswer, actual);
	}
	
	@Test
	public void testgetAMovie() {
		Mockito.when(manager.find(Mockito.eq(Movie.class), Mockito.anyLong())).thenReturn(util.getObjectForJSON(mockObject, Movie.class));
		String expectedAnswer = movierepo.getAMovie(1L);
		String actual = mockObject;
		assertEquals(expectedAnswer, actual);
	}
	
	
//	@Test
//	public void testfindMovie() {
//		String expectedAnswer = movierepo.findMovie(1L);
//		String actual = "{\"message\": \"the account doesn't exist so it couldn't be deleted\"}";
//		assertEquals(expectedAnswer, actual);
//
//	}

}
