package com.frzlyv.transactions.transaction;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PartialUpdateTransactionDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartialUpdateTransactionDto {

  String title;

  BigDecimal amount;

  Long categoryId;

}
