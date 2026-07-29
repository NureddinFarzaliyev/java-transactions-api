package com.frzlyv.transactions.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frzlyv.transactions.user.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * TransactionController
 */
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping("")
  TransactionDto createTransaction(@RequestBody CreateTransactionDto createTransactionDto,
      @AuthenticationPrincipal UserEntity currentUser) {
    return transactionService.createTransaction(currentUser, createTransactionDto);
  }

  @PatchMapping("/{id}")
  TransactionDto updateTransaction(@AuthenticationPrincipal UserEntity currentUser, @PathVariable Long id,
      @RequestBody PartialUpdateTransactionDto partialUpdateTransactionDto) {
    return transactionService.partialUpdateTransaction(currentUser, id, partialUpdateTransactionDto);
  }

  @GetMapping("")
  Page<TransactionDto> getAllTransactions(@AuthenticationPrincipal UserEntity currentUser, Pageable pageable) {
    return transactionService.getAllTransactions(currentUser, pageable);
  }

  @DeleteMapping("/{id}")
  void deleteTransaction(@AuthenticationPrincipal UserEntity currentUser, @PathVariable Long id) {
    transactionService.deleteTransaction(currentUser, id);
  }

}
