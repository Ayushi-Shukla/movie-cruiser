package com.stackroute.moviecruiserserver.exception;

public class UserAlreadyExistsException extends Exception {

//	String message;
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

//	@Override
//	public String toString() {
//		return "UserAlreadyExistsException [message=" + message + "]";
//	}
}
