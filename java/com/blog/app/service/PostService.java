package com.blog.app.service;

import java.util.List;

import com.blog.app.dao.Post;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;

/**
 * Post Service
 */
public interface PostService {

	/**
	 * This method helps to add new post
	 * @param PostDto -- contains post details
	 * @return PostDto -- will return new added post details 
	 */
	public PostDto addPost(final PostDto postDto, final Integer userId, final Integer categoryId);
	
	/**
	 * This method helps to update the post
	 * @param PostDto -- contains post details
	 * @param postId -- contains post id
	 * @return PostDto -- will return updated project details
	 */
	public PostDto updatePost(final PostDto post, final int postId);
	
	/**
	 * This method helps to get a post
	 * @param postId -- contains post id
	 * @return PostDto -- will return a Post details
	 */
	public PostDto getPost(final int postId);
	
	/**
	 * This method helps to find all posts
	 * @return List<PostDto> -- will return all posts
	 */
	public PostResponse getAllPost(final Integer pageNumber, final Integer pageSize, final String sortBy);
	
	/**
	 * This method helps to delete the post
	 * @param postId -- contains post id
	 */
	public String deletePost(final int postId);
	
	/**
	 * This method helps to get the post by category
	 * @param CategoryID -- contains category id
	 * @return List<PostDto> -- will return all posts
	 */
	public PostResponse getPostByCategory(final int CategoryID, final int pageNumber, final int pageSize);
	
	/**
	 * This method helps to get the post by user
	 * @param userId -- contains user id
	 * @return List<PostDto> -- will return list of post
	 */
	public PostResponse getPostByUser(final int userId, final int pageNumber, final int pageSize);
	
	/**
	 * This method helps to search a post
	 * @param keyword -- contains keyword
	 * @return PostDto -- will return a post
	 */
	public List<PostDto> searchPost(final String keyword);
}
