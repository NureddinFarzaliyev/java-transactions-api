package com.frzlyv.transactions.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frzlyv.transactions.user.UserEntity;

/**
 * TransactionService
 */
public interface TransactionService {

  TransactionDto createTransaction(UserEntity currentUser, CreateTransactionDto createTransactionDto);

  Page<TransactionDto> getAllTransactions(UserEntity currentUser, Pageable pageable);

  void deleteTransaction(UserEntity currentUser, Long id);

  TransactionDto partialUpdateTransaction(UserEntity currentUser, Long id,
      PartialUpdateTransactionDto partialUpdateTransactionDto);

}
