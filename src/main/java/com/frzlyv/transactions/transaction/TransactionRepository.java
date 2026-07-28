package com.frzlyv.transactions.transaction;

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

  Optional<TransactionEntity> findByIdAndUserId(Long id, Long userId);

  void deleteByIdAndUserId(Long id, Long userId);

}
