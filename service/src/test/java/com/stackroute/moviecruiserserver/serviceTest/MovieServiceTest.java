package com.stackroute.moviecruiserserver.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;

import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.exception.*;
import com.stackroute.moviecruiserserver.repository.*;
import com.stackroute.moviecruiserserver.service.MovieService;

public class MovieServiceTest {
	
	private Movie movie;
	Optional<Movie> options;
	
	@Mock
	private MovieRepository movieRepo;
	
	@InjectMocks
	private MovieService movieService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		movie=new Movie(1234,1,"POC","good movie","www.abc.com","2015-03-23",45.5,112,"John123");
		options=Optional.of(movie);
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull(movie);
		assertNotNull("jpa repository creation fails: use@injectMocks on movieService",movieRepo);
	}
	
	@Test
	public void testSaveMovieSuccess()throws MovieAlreadyExistsException{
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag =movieService.saveMovie(movie);
		assertEquals("saving movie failed,the call to movieDAOimpl is returning false,"
				+ "please check this method",true,flag);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test(expected=MovieAlreadyExistsException.class)
	public void testSaveMovieFail()throws MovieAlreadyExistsException{
		when(movieRepo.findById(1234)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag =movieService.saveMovie(movie);
	}
	
	@Test
	public void testUpdateMovie()throws MovieNotFoundException{
		when(movieRepo.findById(1234)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("Average Movie");
		final Movie movie1 = movieService.updateMovie(movie);
		assertEquals("Average Movie", movie1.getComments());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testDeleteById() throws MovieNotFoundException{
		when(movieRepo.findById(1234)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		boolean flag=movieService.deleteMovieById(1234);
		assertEquals("Deleting movie failed", true, flag);
		//verify(movieRepo,times(1)).deleteById(1);;
		verify(movieRepo,times(1)).findById(movie.getId());
	}


	
	@Test
	public void testGetMovieById()throws MovieNotFoundException{
		when(movieRepo.findById(1234)).thenReturn(options);
		Movie movie1=movieService.getMovieById(1234);
		assertEquals("fetching movie by id failed",movie1,movie);
		verify(movieRepo,times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetMyMovies(){
		List<Movie> movieList=new ArrayList<>();
		movieList.add(movie);
		when(movieRepo.findByUserId("ashu")).thenReturn(movieList);
		List<Movie> movie1=movieService.getMyMovies("ashu");
		assertEquals(movieList,movie1);
		verify(movieRepo,times(1)).findByUserId("ashu");
	}
	
}
