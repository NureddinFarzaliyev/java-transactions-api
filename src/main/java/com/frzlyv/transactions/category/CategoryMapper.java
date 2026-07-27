
package com.frzlyv.transactions.category;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.shared.Mapper;

/**
 * CategoryMapper
 */
@Component
public class CategoryMapper implements Mapper<CategoryEntity, CategoryDto> {

  private ModelMapper modelMapper;

  public CategoryMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public CategoryDto toDto(CategoryEntity entity) {
    return modelMapper.map(entity, CategoryDto.class);
  }

  @Override
  public CategoryEntity toEntity(CategoryDto dto) {
    return modelMapper.map(dto, CategoryEntity.class);
  }

}
