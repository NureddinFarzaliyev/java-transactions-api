package com.frzlyv.transactions.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  Optional<CategoryEntity> findByIdAndUserId(Long id, Long userId);

  List<CategoryEntity> findByUserId(Long userId);

  boolean existsByIdAndUserId(Long id, Long userId);

  void deleteByIdAndUserId(Long id, Long userId);

}
