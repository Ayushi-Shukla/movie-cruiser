package com.stackroute.moviecruiserserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.exception.*;
import com.stackroute.moviecruiserserver.repository.*;

@Service
public class MovieService implements MovieServiceInterface {
	
	@Autowired
	private MovieRepository movierepository;

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Optional<Movie> obj=movierepository.findById(movie.getId());
		if(obj.isPresent()){
		throw new MovieAlreadyExistsException("Movie aready exists, cannot be saved");
		}
		movierepository.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		Movie obj = movierepository.findById(movie.getId()).orElse(null);
		if(obj==null){
			throw new MovieNotFoundException("Movie not found, cannot be updated.");
		}
		obj.setComments(movie.getComments());
		movierepository.save(obj);
		return obj;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		Movie obj = movierepository.findById(id).orElse(null);
		if (obj==null){
			throw new MovieNotFoundException("Movie not found, cannot be deleted.");
		}
		movierepository.delete(obj);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Movie obj=movierepository.findById(id).get();
		if(obj==null){
			throw new MovieNotFoundException("Movie not found");
		}
		return obj;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movierepository.findByUserId(userId);
	}

}
