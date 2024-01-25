package com.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.app.dao.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
