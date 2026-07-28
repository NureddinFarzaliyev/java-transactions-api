package com.frzlyv.transactions.transaction;

import java.math.BigDecimal;

import com.frzlyv.transactions.category.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransactionDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

  Long id;
  String title;
  BigDecimal amount;
  CategoryDto category;

}
