package com.blog.app.service;

import java.util.List;

import com.blog.app.payload.CategoryDto;

public interface CategoryService {

	/**
	 * This method helps to add new category
	 * @param categoryDto -- contains category details
	 * @return CategoryDto -- will return CategoryDto
	 */
	public CategoryDto addCategory(final CategoryDto categoryDto);
	
	/**
	 * This method helps to update the category
	 * @param categoryDto -- contains CategoryDto
	 * @param categoryId -- contains category id
	 * @return CategoryDto -- will return CategoryDto
	 */
	public CategoryDto updateCategory(final CategoryDto categoryDto, final int categoryId);
	
	/**
	 * This method helps to get category
	 * @param categoryId -- contains category id
	 * @return CategoryDto -- will return CategoryDto
	 */
	public CategoryDto getCategory(final int categoryId);
	
	/**
	 * This method will helps to get all categories
	 * @return List<CategoryDto> -- will return list of all categories
	 */
	public List<CategoryDto> getAllCategories();
	
	/**
	 * This method helps to delete the category
	 * @param categoryId -- contains category id
	 */
	public void deleteCategory(final Integer categoryId);
}
