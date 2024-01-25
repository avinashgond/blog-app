package com.blog.app.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private int categoryId;
	
	@NotEmpty
	@Size(min = 2, max=50, message="title should have atleast 4 characters")
	private String categoryTitle;

	private String categoryDescription;
}
