package com.frzlyv.transactions.transaction;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.shared.Mapper;

/**
 * TransactionMapper
 */
@Component
public class TransactionMapper implements Mapper<TransactionEntity, TransactionDto> {

  ModelMapper modelMapper;

  public TransactionMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public TransactionEntity toEntity(TransactionDto dto) {
    return modelMapper.map(dto, TransactionEntity.class);
  }

  @Override
  public TransactionDto toDto(TransactionEntity entity) {
    return modelMapper.map(entity, TransactionDto.class);
  }

}
