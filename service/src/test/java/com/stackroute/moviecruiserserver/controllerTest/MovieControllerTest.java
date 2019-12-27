package com.stackroute.moviecruiserserver.controllerTest;

import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserver.controller.*;
import com.stackroute.moviecruiserserver.domain.Movie;
import com.stackroute.moviecruiserserver.service.MovieService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	
	@Autowired
	private MockMvc movieMockMvc;
	
	@MockBean
	private MovieService movieservice;
	
	@InjectMocks
	private MovieController movieController;
	
	private Movie movie;
	List<Movie> movies;
	
	@Before
	public void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		movies=new ArrayList<>();
		movie=new Movie(1234,3706,"Batman begins","very good","22/11/2015",null,4.9,33,"John123");
		movies.add(movie);
        movie=new Movie(1236,3766,"Batman begins2","good","22/11/2010",null,4.9,33,"John123");
        movies.add(movie);
		movieMockMvc=MockMvcBuilders.standaloneSetup(movieController).build();
		
	}
	

	
	@Test
	public void testSaveMovie()throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NTI4OTI1NDR9.gQx6PkIaIy-l76uf-fZJirhhKSAsKKm16A8IRic1MiA";
		when(movieservice.saveMovie(movie)).thenReturn(true);
		movieMockMvc.perform(post("/api/v1/movieservice/movie").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated());
		verify(movieservice,times(1)).saveMovie(Mockito.any(Movie.class));
	}

	
	@Test
	public void testUpdateMovieSuccess() throws Exception {
		
	    movie.setComments("bad");
        when(movieservice.updateMovie(movie)).thenReturn(movies.get(0));
        movieMockMvc.perform(put("/api/v1/movieservice/movie").contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk());
        verify(movieservice, times(1)).updateMovie(Mockito.any(Movie.class));
        verifyNoMoreInteractions(movieservice);
	}
	
	@Test
	public void testDeleteMovieById() throws Exception {
		when(movieservice.deleteMovieById(1)).thenReturn(true);
		movieMockMvc.perform(delete("/api/v1/movieservice/movie/{id}", 1)).andExpect(status().isOk());
		verify(movieservice, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieservice);
	}
	
	@Test
	public void testGetMovieById() throws Exception {
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NTI4OTI1NDR9.gQx6PkIaIy-l76uf-fZJirhhKSAsKKm16A8IRic1MiA";
		when(movieservice.getMovieById(1)).thenReturn(movie);
		movieMockMvc.perform(get("/api/v1/movieservice/movie/{id}", 1).header("autorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieservice, times(1)).getMovieById(1);
		verifyNoMoreInteractions(movieservice);
	}

	@Test
	public void testGetAllMovies() throws Exception {
		
		 
		 List <Movie> movies=new ArrayList<>();
		Movie movie1=new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123");
		movies.add(movie1);
		Movie movie2=new Movie(1234,1,"POC-2","good movie","www.abc.com","2017-04-26",65.5,112,"John123");
		movies.add(movie2);
		
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NTI4OTI1NDR9.gQx6PkIaIy-l76uf-fZJirhhKSAsKKm16A8IRic1MiA";
		when(movieservice.getMyMovies("100")).thenReturn(movies);
		movieMockMvc.perform(get("/api/v1/movieservice/movies").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk());
		verify(movieservice, times(1)).getMyMovies("100");
		verifyNoMoreInteractions(movieservice);
	}



	private String jsonToString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
			}
	}
}
