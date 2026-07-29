package com.frzlyv.transactions.report;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.frzlyv.transactions.transaction.TransactionEntity;
import com.frzlyv.transactions.transaction.TransactionRepository;
import com.frzlyv.transactions.user.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * ReportServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final TransactionRepository transactionRepository;

  @Override
  public GeneralReportDto getGeneralReport(UserEntity currentUser) {

    List<TransactionEntity> transactionEntities = transactionRepository.findAllByUserId(currentUser.getId());

    BigDecimal totalExpense = transactionEntities.stream().map(TransactionEntity::getAmount)
        .filter((BigDecimal amount) -> amount != null && amount.compareTo(BigDecimal.ZERO) <= 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalIncome = transactionEntities.stream().map(TransactionEntity::getAmount)
        .filter((BigDecimal amount) -> amount != null && amount.compareTo(BigDecimal.ZERO) > 0)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal total = totalExpense.add(totalIncome);

    GeneralReportDto reportDto = GeneralReportDto.builder()
        .totalExpense(totalExpense)
        .totalIncome(totalIncome)
        .total(total)
        .build();

    return reportDto;
  }

}
