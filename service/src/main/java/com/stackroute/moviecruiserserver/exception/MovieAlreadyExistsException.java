package com.stackroute.moviecruiserserver.exception;

@SuppressWarnings("serial")
public class MovieAlreadyExistsException extends Exception {

	public MovieAlreadyExistsException(String message) {
		super(message);
	}

}
