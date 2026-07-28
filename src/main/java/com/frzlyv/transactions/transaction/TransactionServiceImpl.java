package com.frzlyv.transactions.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frzlyv.transactions.category.CategoryEntity;
import com.frzlyv.transactions.category.CategoryRepository;
import com.frzlyv.transactions.shared.Mapper;
import com.frzlyv.transactions.user.UserEntity;
import com.frzlyv.transactions.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * TransactionServiceImpl
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  private CategoryRepository categoryRepository;
  private TransactionRepository transactionRepository;
  private UserRepository userRepository;
  private Mapper<TransactionEntity, TransactionDto> transactionMapper;

  public TransactionServiceImpl(CategoryRepository categoryRepository, TransactionRepository transactionRepository,
      UserRepository userRepository, Mapper<TransactionEntity, TransactionDto> transactionMapper) {
    this.categoryRepository = categoryRepository;
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public TransactionDto createTransaction(UserEntity currentUser, CreateTransactionDto createTransactionDto) {

    CategoryEntity categoryEntity = categoryRepository
        .findByIdAndUserId(createTransactionDto.getCategoryId(), currentUser.getId())
        .orElseThrow(() -> new EntityNotFoundException("Category does not exist."));

    UserEntity userEntity = userRepository.findById(currentUser.getId())
        .orElseThrow(() -> new EntityNotFoundException("User not found."));

    TransactionEntity transactionEntity = TransactionEntity.builder()
        .title(createTransactionDto.title)
        .amount(createTransactionDto.getAmount())
        .user(userEntity)
        .category(categoryEntity)
        .build();

    TransactionEntity savedTransaction = transactionRepository.save(transactionEntity);
    return transactionMapper.toDto(savedTransaction);

  }

  @Override
  public Page<TransactionDto> getAllTransactions(UserEntity currentUser, Pageable pageable) {
    Page<TransactionEntity> entities = transactionRepository.findAllByUserId(pageable, currentUser.getId());
    return entities.map(transactionMapper::toDto);
  }

  @Override
  @Transactional
  public void deleteTransaction(UserEntity currentUser, Long id) {
    transactionRepository.deleteByIdAndUserId(id, currentUser.getId());
  }

  public TransactionDto partialUpdateTransaction(UserEntity currentUser, Long id,
      PartialUpdateTransactionDto partialUpdateTransactionDto) {

    TransactionEntity transactionEntity = transactionRepository
        .findByIdAndUserId(id, currentUser.getId())
        .orElseThrow(() -> new EntityNotFoundException("Transaction does not exist."));

    if (partialUpdateTransactionDto.getTitle() != null) {
      transactionEntity.setTitle(partialUpdateTransactionDto.getTitle());
    }
    if (partialUpdateTransactionDto.getAmount() != null) {
      transactionEntity.setAmount(partialUpdateTransactionDto.getAmount());
    }
    if (partialUpdateTransactionDto.getCategoryId() != null) {
      CategoryEntity categoryEntity = categoryRepository
          .findByIdAndUserId(partialUpdateTransactionDto.getCategoryId(), currentUser.getId())
          .orElseThrow(() -> new EntityNotFoundException("Category does not exist."));
      transactionEntity.setCategory(categoryEntity);
    }

    TransactionEntity updatedTransaction = transactionRepository.save(transactionEntity);
    return transactionMapper.toDto(updatedTransaction);
  }

}
