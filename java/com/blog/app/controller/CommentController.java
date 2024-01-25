package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payload.CommentDto;
import com.blog.app.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
		CommentDto response = commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId){
		 commentService.deletePost(commentId);
		 return new ResponseEntity<String>("Comment deleted succesfully for : " + commentId, HttpStatus.OK);
	}
}
