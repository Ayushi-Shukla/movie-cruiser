package com.stackroute.moviecruiserserver.domain;

import javax.persistence.*;

@Entity
@Table(name="movie")
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="movie_id")
	private int movieId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="poster_path")
	private String poster_path;
	
	@Column(name="release_date")
	private String release_date;
	
	@Column(name="vote_average")
	private Double voteAverage;
	
	@Column(name="vote_count")
	private int voteCount;
	
	@Column(name="user_id")
	private String userId;
	
	public Movie() {
		super();
	}

	public Movie(int id, int movieId, String title, String comments, String poster_path, String release_date,
			Double voteAverage, int voteCount, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}