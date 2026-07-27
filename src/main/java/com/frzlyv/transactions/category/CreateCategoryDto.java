package com.frzlyv.transactions.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateCategoryDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryDto {

  @NotBlank(message = "Title cannot be blank.")
  String title;

}
