package com.blog.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.payload.FileResponse;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;
import com.blog.app.repositories.PostRepo;
import com.blog.app.service.FileService;
import com.blog.app.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user-id/{userId}/category-id/{categoryId}")
	public ResponseEntity<PostDto> addpost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto addedPost = postService.addPost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(addedPost, HttpStatus.CREATED);
	}
	
	@RequestMapping("/category-id/{categoryId}")
	public ResponseEntity<PostResponse> getAllPostByCategoryId(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "pageSize") int pageSize
			){
	PostResponse allPosts = postService.getPostByCategory(categoryId, pageNumber, pageSize);
	return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}
	
	@RequestMapping("/user-id/{userId}")
	public ResponseEntity<PostResponse> getAllPostByUserId(
			@PathVariable Integer userId,
			@RequestParam(value = "pageNumber") Integer pageNumber,
			@RequestParam(value = "pageSize") Integer pageSize
			){
	PostResponse allPosts = postService.getPostByUser(userId, pageNumber, pageSize);
	return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}
	
	@RequestMapping("/update-post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post, @PathVariable Integer postId){
		PostDto updatedPost = postService.updatePost(post, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
	}
	
	@RequestMapping("/post-id/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
		PostDto post = postService.getPost(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@RequestMapping("/all-post")
	public ResponseEntity<PostResponse> getAllPosts(
		@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
	    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
	    @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
		){
		return new ResponseEntity<PostResponse>(postService.getAllPost(pageNumber,pageSize, sortBy),HttpStatus.OK);
	}
	
	@RequestMapping("/delete-post/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		String response = postService.deletePost(postId);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@RequestMapping("/search")
	public ResponseEntity<List<PostDto>> searchByTitle(@RequestParam(value = "title") String title){
		return new ResponseEntity<List<PostDto>>(postService.searchPost(title),HttpStatus.OK);
	}
	
	@RequestMapping("/file-upload/post-id/{postId}")
	public ResponseEntity<?> uploadFile(
			@RequestParam MultipartFile image,
			@PathVariable Integer postId)
	{
		PostDto updatePost = null;
		try {
			PostDto postDto = postService.getPost(postId);
			String fileName = fileService.uploadImage(path, image);
			postDto.setImageName(fileName);
			updatePost = postService.updatePost(postDto, postId);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.ACCEPTED);
	}
}
