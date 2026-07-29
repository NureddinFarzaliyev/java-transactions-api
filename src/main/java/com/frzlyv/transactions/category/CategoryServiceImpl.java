package com.frzlyv.transactions.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frzlyv.transactions.shared.Mapper;
import com.frzlyv.transactions.shared.exceptions.CannotDeleteBecauseOfConflictException;
import com.frzlyv.transactions.shared.exceptions.EntityDoesNotExistException;
import com.frzlyv.transactions.transaction.TransactionRepository;
import com.frzlyv.transactions.user.UserEntity;
import com.frzlyv.transactions.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * CategoryServiceImpl
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final Mapper<CategoryEntity, CategoryDto> modelMapper;
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;

  @Override
  public CategoryDto createCategory(UserEntity currentUser, CreateCategoryDto createCategoryDto) {
    UserEntity user = userRepository.findById(currentUser.getId()).orElseThrow();

    CategoryEntity categoryEntity = CategoryEntity.builder()
        .title(createCategoryDto.getTitle())
        .user(user)
        .build();

    CategoryEntity savedCategory = categoryRepository.save(categoryEntity);

    return modelMapper.toDto(savedCategory);

  }

  @Override
  @Transactional
  public CategoryDto editCategory(Long id, UserEntity currentUser, CreateCategoryDto createCategoryDto) {
    CategoryEntity existingCategory = categoryRepository.findByIdAndUserId(id, currentUser.getId())
        .orElseThrow(() -> new EntityDoesNotExistException("Category does not exists."));

    existingCategory.setTitle(createCategoryDto.getTitle());
    CategoryEntity savedCategory = categoryRepository.save(existingCategory);

    return modelMapper.toDto(savedCategory);

  }

  @Override
  @Transactional
  public void deleteCategory(Long id, UserEntity currentUser) {
    if (!categoryRepository.existsByIdAndUserId(id, currentUser.getId())) {
      throw new EntityDoesNotExistException("Category does not exists.");
    }
    if (transactionRepository.existsByUserIdAndCategoryId(currentUser.getId(), id)) {
      throw new CannotDeleteBecauseOfConflictException("This category has transactions.");
    }
    categoryRepository.deleteByIdAndUserId(id, currentUser.getId());
  }

  @Override
  public List<CategoryDto> getAllCategories(UserEntity currentUser) {
    List<CategoryEntity> entityList = categoryRepository.findByUserId(currentUser.getId());
    return entityList.stream().map(modelMapper::toDto).toList();
  }

}
