package com.frzlyv.transactions.category;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frzlyv.transactions.user.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * CategoryController
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping("")
  public CategoryDto createCategory(@RequestBody CreateCategoryDto createCategoryDto,
      @AuthenticationPrincipal UserEntity currentUser) {
    return categoryService.createCategory(currentUser, createCategoryDto);
  }

  @PutMapping("/{id}")
  public CategoryDto updateCategory(@RequestBody CreateCategoryDto createCategoryDto,
      @AuthenticationPrincipal UserEntity currentUser,
      @PathVariable Long id) {
    return categoryService.editCategory(id, currentUser, createCategoryDto);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@AuthenticationPrincipal UserEntity currentUser, @PathVariable Long id) {
    categoryService.deleteCategory(id, currentUser);
  }

  @GetMapping("")
  public List<CategoryDto> getTest(@AuthenticationPrincipal UserEntity currentUser) {
    return categoryService.getAllCategories(currentUser);
  }

}
