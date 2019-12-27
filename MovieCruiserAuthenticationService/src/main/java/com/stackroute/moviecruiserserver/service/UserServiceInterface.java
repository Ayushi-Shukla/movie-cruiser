package com.stackroute.moviecruiserserver.service;

import java.util.*;

import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.exception.*;

public interface UserServiceInterface {
	
	boolean saveUser(User user) throws UserAlreadyExistsException;
	public User findByUserIdAndPassword(String userId,String password)throws UserNotFoundException;
//	Movie updateMovie(Movie movie) throws UserNotFoundException;
//	boolean deleteMovieById(int id) throws UserNotFoundException;
//	Movie getMovieById(int id) throws MovieNotFoundException;
//	List<Movie> getAllMovies() throws MovieNotFoundException;
}
