package com.frzlyv.transactions.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CategoryDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

  Long id;

  String title;

}
