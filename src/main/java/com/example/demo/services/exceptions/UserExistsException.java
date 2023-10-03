package com.example.demo.services.exceptions;

public class UserExistsException extends Exception{

	private static final long serialVersionUID=1904585489;
	
	public UserExistsException(String message) {
		super(message);
	}
	
}
