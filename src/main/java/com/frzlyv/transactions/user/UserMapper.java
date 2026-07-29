package com.frzlyv.transactions.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.shared.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * UserMapper
 */
@RequiredArgsConstructor
@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {

  private final ModelMapper modelMapper;

  @Override
  public UserDto toDto(UserEntity entity) {
    return modelMapper.map(entity, UserDto.class);
  }

  @Override
  public UserEntity toEntity(UserDto dto) {
    return modelMapper.map(dto, UserEntity.class);
  }

}
