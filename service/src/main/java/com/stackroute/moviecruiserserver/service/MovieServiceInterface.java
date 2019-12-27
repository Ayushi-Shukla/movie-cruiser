package com.stackroute.moviecruiserserver.service;

import java.util.*;

import com.stackroute.moviecruiserserver.domain.Movie;
import com.stackroute.moviecruiserserver.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserver.exception.MovieNotFoundException;

public interface MovieServiceInterface {
	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	
	Movie getMovieById(int id) throws MovieNotFoundException;
	
	List<Movie> getMyMovies(String userId);
}
