package com.stackroute.moviecruiserserver.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

//	String message;
//
//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}

	public UserNotFoundException(String message) {
		super(message);
	}

//	@Override
//	public String toString() {
//		return "UserNotFoundException [message=" + message + "]";
//	}

}
