package com.blog.app.service;

import com.blog.app.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(final CommentDto comment, final Integer postId);
	void deletePost(final Integer commentId);
	
}
