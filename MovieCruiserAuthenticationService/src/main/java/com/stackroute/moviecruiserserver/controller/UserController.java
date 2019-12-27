package com.stackroute.moviecruiserserver.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.moviecruiserserver.exception.*;
import com.stackroute.moviecruiserserver.service.*;
import com.stackroute.moviecruiserserver.domain.*;


@CrossOrigin
@RestController
@RequestMapping("api/v1/userservice")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private SecurityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?>registerUser(@RequestBody User user){
		try{
			
			userservice.saveUser(user);
		return new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
		}
		catch(Exception e){
		return new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?>loginUser(@RequestBody User logindetail){
		try{
			String userId=logindetail.getUserId();
			String password=logindetail.getPassword();
			if (userId==null || password==null){
				throw new Exception("Username or password cannot be empty");
			}
			User user=userservice.findByUserIdAndPassword(userId, password);
			if (user==null){
				throw new Exception("User with given Id does not exist");
			}
			String pwd=user.getPassword();
			if (!password.equals(pwd)){
				throw new Exception("Invalid login credential,Please check username and password");
			}
			Map<String,String> map=tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		}
		catch(Exception e){
		return new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.UNAUTHORIZED);
		}
	}
}
	
	
//	@PutMapping("/update")
//	public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
//		ResponseEntity<?> response;
//		try{
//			Movie updatedmovie=movieservice.updateMovie(movie);
//			response=new ResponseEntity<Movie>(updatedmovie,HttpStatus.OK);
//		}
//		catch(MovieNotFoundException e){
//			response=new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
//		}
//		return response;
//	}
//	
//	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
//		ResponseEntity response;
//		try{
//			movieservice.deleteMovieById(id);
//			response=new ResponseEntity<Movie>(HttpStatus.OK);
//		}
//		catch(MovieNotFoundException e){
//			response=new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
//		}
//		return response;
//	}
//	
//	
//	@GetMapping("/getmovie/{id}")
//	public ResponseEntity<?> getMovie(@PathVariable("id") int id){
//		ResponseEntity response;
//		try{
//			Movie movie=movieservice.getMovieById(id);
//			response=new ResponseEntity<Movie>(movie, HttpStatus.OK);
//		}
//		catch(MovieNotFoundException e){
//			response=new ResponseEntity<String>("{\"message\": \""+e.getMessage()+"\"}",HttpStatus.NOT_FOUND);
//		}
//		return response;
//	}
//	
//	@GetMapping("/getallmovies")
//	public ResponseEntity<List<Movie>> getAllMovies(){
//		return new ResponseEntity<List<Movie>>(movieservice.getAllMovies(),HttpStatus.OK);
//	}
