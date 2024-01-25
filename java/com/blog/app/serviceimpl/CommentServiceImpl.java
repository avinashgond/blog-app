package com.blog.app.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.dao.Comment;
import com.blog.app.dao.Post;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.CommentDto;
import com.blog.app.repositories.CommentRepository;
import com.blog.app.repositories.PostRepo;
import com.blog.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto comment, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Invalid post id : " + postId));
		Comment comments = modelMapper.map(comment, Comment.class);
		comments.setPost(post);
		Comment savedComment = commentRepository.save(comments);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deletePost(Integer commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Invalid comment id : " + commentId));
		commentRepository.delete(comment);
	}

}
