package com.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payload.CategoryDto;
import com.blog.app.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> addcategory(@Valid @RequestBody CategoryDto categoryDto){
	CategoryDto newCategory = categoryService.addCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(newCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int categoryId){
		CategoryDto updatedcategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedcategory, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getcategoryById(@PathVariable int categoryId){
		return new ResponseEntity<CategoryDto>(categoryService.getCategory(categoryId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deletecategory(@PathVariable int categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>("Successfully Deleted !!",HttpStatus.OK);
	}
}
