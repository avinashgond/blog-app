package com.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.app.dao.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
