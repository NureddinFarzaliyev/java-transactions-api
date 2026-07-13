package com.frzlyv.transactions.user;

import org.modelmapper.ModelMapper;

import com.frzlyv.transactions.shared.Mapper;

/**
 * UserMapper
 */
public class UserMapper implements Mapper<UserEntity, UserDto> {

  private final ModelMapper modelMapper;

  public UserMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public UserDto toDto(UserEntity entity) {
    return modelMapper.map(entity, UserDto.class);
  }

  @Override
  public UserEntity toEntity(UserDto dto) {
    return modelMapper.map(dto, UserEntity.class);
  }

}
