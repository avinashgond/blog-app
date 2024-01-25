package com.blog.app.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseMessage {
	
	private boolean success;
	private String message;
	
	public ErrorResponseMessage(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	

}
