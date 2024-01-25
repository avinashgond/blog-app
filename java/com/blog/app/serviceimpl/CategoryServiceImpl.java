package com.blog.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.dao.Category;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.CategoryDto;
import com.blog.app.repositories.CategoryRepo;
import com.blog.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category existingcategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Resource not found for given category id "+categoryId));
		existingcategory.setCategoryTitle(categoryDto.getCategoryTitle());
		existingcategory.setCategoryDescription(categoryDto.getCategoryDescription());
		return modelMapper.map(categoryRepo.save(existingcategory), CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category foundcategory = categoryRepo.findById(categoryId).orElseThrow(()->  new ResourceNotFoundException("Resource not found for given category id "+categoryId));
		return modelMapper.map(foundcategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> allCategories = categoryRepo.findAll();
		List<CategoryDto> allCategoriesDto = allCategories.stream().map(cat->modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return allCategoriesDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Resource not found for given category id "+categoryId));
		categoryRepo.deleteById(categoryId);
	}
}
