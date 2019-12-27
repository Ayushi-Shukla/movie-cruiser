package com.stackroute.moviecruiserserver.controller;

import java.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.exception.*;
import com.stackroute.moviecruiserserver.service.*;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/movieservice")
public class MovieController {
	
	@Autowired
	private MovieService movieservice;
	
	//< - - Save Method - - >
	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie,
			HttpServletRequest request,HttpServletRequest response){
		System.out.println("Saving movie");
		String authHeader=request.getHeader("authorization");
		String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId: "+userId);		
		try{
			System.out.println(movie.getMovieId());
			movie.setUserId(userId);
			movie.setMovieId(movie.getMovieId());
			movieservice.saveMovie(movie);
		}
		catch(MovieAlreadyExistsException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/movie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
		Movie fetchedMovie;
		try{
			fetchedMovie=movieservice.updateMovie( movie);
		}
		catch(MovieNotFoundException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(fetchedMovie,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
		try{
			movieservice.deleteMovieById(id);
		}
		catch(MovieNotFoundException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Movie deleted successfully",HttpStatus.OK);
	}
	
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovie(@PathVariable("id") int id){
		Movie thisMovie;
		try{
			thisMovie=movieservice.getMovieById(id);
		}
		catch(MovieNotFoundException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(thisMovie, HttpStatus.OK);
	}
	
	@GetMapping("/movies")
	public @ResponseBody ResponseEntity<List<Movie>> getMyMovies(ServletRequest req, ServletResponse res){
		HttpServletRequest request=(HttpServletRequest) req;
		String authHeader=request.getHeader("authorization");
		String token=authHeader.substring(7);
		String userId=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return new ResponseEntity<List<Movie>>(movieservice.getMyMovies(userId),HttpStatus.OK);
	}
	
}
