package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.IMovieService;
import com.qa.persistence.domain.Movie;

@Path("/movie")
public class MovieEndpoint {
	
	@Inject
	private IMovieService service;
	
	
	@GET
	@Path ("/json")
	@Produces ({"application/json"})
	
	public String getAllMovies() {
		
		return service.getAllMovies();
	}
	
	
	@GET
	@Path ("/json/{id}")
	@Produces ({"application/json"})
	public Movie getAMovie(@PathParam("id") Long id) {
		
		return service.getAMovie(id);
	}
	
	@POST
	@Path ("/json")
	@Produces ({"application/json"})
	public Movie createAMovie (String movie) {
		
		return service.createAMovie(movie);
	}
	
	@PUT
	@Path ("/json")
	@Produces ({"application/json"})
	public String updateAMovie (String movie) {
		
		return service.updateAMovie(movie);
	}
	
	@DELETE
	@Path ("/json/{id}")
	@Produces ({"application/json"})
	public String deleteAMovie (@PathParam("id") Long id) {
		
		return service.deleteAMovie(id);
	}
	
	
	

}
