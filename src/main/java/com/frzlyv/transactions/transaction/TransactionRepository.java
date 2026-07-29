package com.frzlyv.transactions.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionRepository
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

  Page<TransactionEntity> findAllByUserId(Pageable pageable, Long userId);

  List<TransactionEntity> findAllByUserId(Long userId);

  boolean existsByUserIdAndCategoryId(Long userId, Long categoryId);

  Optional<TransactionEntity> findByIdAndUserId(Long id, Long userId);

  void deleteByIdAndUserId(Long id, Long userId);

}
