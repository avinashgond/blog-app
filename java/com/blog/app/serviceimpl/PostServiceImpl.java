package com.blog.app.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.dao.Category;
import com.blog.app.dao.Post;
import com.blog.app.dao.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;
import com.blog.app.repositories.CategoryRepo;
import com.blog.app.repositories.PostRepo;
import com.blog.app.repositories.UserRepository;
import com.blog.app.service.PostService;

@Service
public class PostServiceImpl implements PostService
{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto addPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid user id:" + userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Invalid category id" + categoryId));
		Post newPost = modelMapper.map(postDto, Post.class);
		newPost.setAddedDate(new Date());
		newPost.setCategory(category);
		newPost.setUser(user);
		newPost.setImageName("default.png");
		Post addedPost = postRepo.save(newPost);
		return modelMapper.map(addedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto post, int postId) {
		Post existedPost = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Invalid Post Id : " + postId));
		existedPost.setAddedDate(new Date());
		existedPost.setContent(post.getContent());
		existedPost.setTitle(post.getTitle());
		postRepo.save(existedPost);
		return modelMapper.map(existedPost, PostDto.class);
	}

	@Override
	public PostDto getPost(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Invalid post id : " + postId));
		return modelMapper.map( post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pageOfPosts = postRepo.findAll(p);
		List<Post> allPosts = pageOfPosts.getContent();
		List<PostDto> allPostDto = allPosts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostDto);
		postResponse.setPageNumber(pageOfPosts.getNumber());
		postResponse.setPageSize(pageOfPosts.getSize());
		postResponse.setTotalElements(pageOfPosts.getTotalElements());
		postResponse.setTotalPages(pageOfPosts.getTotalPages());
		postResponse.setLastPage(pageOfPosts.isLast());
		return postResponse;
	}

	@Override
	public String deletePost(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Invalid post id : " + postId));	
		postRepo.delete(post);
		return "Successfully Deleted !";
	}

	@Override
	public PostResponse getPostByCategory(int CategoryID, int pageNumber, int pageSize) {
		Pageable pageOfPost = PageRequest.of(pageNumber, pageSize);
		Category category = categoryRepo.findById(CategoryID).orElseThrow(()->new ResourceNotFoundException("Invalid Category id : " + CategoryID));
		Page<Post> p = postRepo.findByCategory(category,pageOfPost);
		List<Post> posts = p.getContent();
		List<PostDto> allPosts = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPosts);
		postResponse.setPageNumber(p.getNumber());
		postResponse.setPageSize(p.getSize());
		postResponse.setTotalElements(p.getTotalElements());
		postResponse.setTotalPages(p.getTotalPages());
		postResponse.setLastPage(p.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(int userId, int pageNumber, int pageSize) {
		Pageable pageOfPost = PageRequest.of(pageNumber, pageSize);
		
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Invalid user id : " + userId));
		Page<Post> p = postRepo.findByUser(user,pageOfPost);
		List<Post> posts = p.getContent();
		List<PostDto> allPosts = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPosts);
		postResponse.setPageNumber(p.getNumber());
		postResponse.setPageSize(p.getSize());
		postResponse.setTotalElements(p.getTotalElements());
		postResponse.setTotalPages(p.getTotalPages());
		postResponse.setLastPage(p.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> allPosts = postRepo.findByTitleContaining(keyword);
		System.out.println(allPosts);
		return allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
