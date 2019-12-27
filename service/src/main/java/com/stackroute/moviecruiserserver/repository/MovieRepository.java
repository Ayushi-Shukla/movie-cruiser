package com.stackroute.moviecruiserserver.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.moviecruiserserver.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
	
	List<Movie> findByUserId(String userId);
}
