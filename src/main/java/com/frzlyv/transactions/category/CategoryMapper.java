
package com.frzlyv.transactions.category;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.shared.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * CategoryMapper
 */
@Component
@RequiredArgsConstructor
public class CategoryMapper implements Mapper<CategoryEntity, CategoryDto> {

  private final ModelMapper modelMapper;

  @Override
  public CategoryDto toDto(CategoryEntity entity) {
    return modelMapper.map(entity, CategoryDto.class);
  }

  @Override
  public CategoryEntity toEntity(CategoryDto dto) {
    return modelMapper.map(dto, CategoryEntity.class);
  }

}
