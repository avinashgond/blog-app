package com.blog.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.app.dao.Category;
import com.blog.app.dao.Post;
import com.blog.app.dao.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(final User user, final Pageable pageOfPost);
	
	Page<Post> findByCategory(final Category category,final Pageable pageOfPost);
	
	List<Post> findByTitleContaining(final String title);
}
