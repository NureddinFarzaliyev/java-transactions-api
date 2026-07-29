package com.frzlyv.transactions.transaction;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.shared.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * TransactionMapper
 */
@Component
@RequiredArgsConstructor
public class TransactionMapper implements Mapper<TransactionEntity, TransactionDto> {

  private final ModelMapper modelMapper;

  @Override
  public TransactionEntity toEntity(TransactionDto dto) {
    return modelMapper.map(dto, TransactionEntity.class);
  }

  @Override
  public TransactionDto toDto(TransactionEntity entity) {
    return modelMapper.map(entity, TransactionDto.class);
  }

}
