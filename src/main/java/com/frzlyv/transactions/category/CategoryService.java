package com.frzlyv.transactions.category;

import java.util.List;

import com.frzlyv.transactions.user.UserEntity;

/**
 * CategoryService
 */
public interface CategoryService {

  CategoryDto createCategory(UserEntity currentUser, CreateCategoryDto createCategoryDto);

  CategoryDto editCategory(Long id, UserEntity currentUser, CreateCategoryDto createCategoryDto);

  void deleteCategory(Long id, UserEntity currentUser);

  List<CategoryDto> getAllCategories(UserEntity currentUser);

}
