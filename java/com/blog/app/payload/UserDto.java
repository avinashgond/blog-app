package com.blog.app.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min =4, message = "name should contain 4 characters atleast")
	private String name;
	
	@NotEmpty
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" , message = "Email is not valid")
	private String email;
	
	@NotEmpty
	@Pattern(
			regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "A password contains at least eight characters, "
			+ "including at least one number and includes both lower and uppercase letters and "
			+ "special characters, for example #, ?, !.")
	private String password;
	
	private String about;
}
