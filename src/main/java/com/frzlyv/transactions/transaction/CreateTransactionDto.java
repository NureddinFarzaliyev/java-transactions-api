package com.frzlyv.transactions.transaction;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateTransactionDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDto {

  @NotBlank(message = "Title is required.")
  String title;

  @NotBlank(message = "Amount is required.")
  BigDecimal amount;

  @NotBlank(message = "Category is required.")
  Long categoryId;

}
