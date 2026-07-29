package com.frzlyv.transactions.report;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GeneralReportDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralReportDto {

  BigDecimal totalIncome;
  BigDecimal totalExpense;
  BigDecimal total;

}
