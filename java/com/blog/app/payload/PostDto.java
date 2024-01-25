package com.blog.app.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.app.dao.Category;
import com.blog.app.dao.Comment;
import com.blog.app.dao.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class PostDto {
	
	private Integer postId;

	private String title;

	private String content;
	
	private String imageName;
	
	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private List<CommentDto> comments = new ArrayList<>();
}
