package com.blog.app.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5453928409086011358L;

	public ResourceNotFoundException() {
		super("Resource not found for given id");
	}
	
	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
