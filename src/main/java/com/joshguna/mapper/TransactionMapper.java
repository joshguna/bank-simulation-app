package com.joshguna.mapper;

import com.joshguna.dto.AccountDTO;
import com.joshguna.dto.TransactionDTO;
import com.joshguna.entity.Account;
import com.joshguna.entity.Transaction;
import org.modelmapper.ModelMapper;

public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDTO convertToDTO(Transaction entity) {
        //this method will accept Account entity and will convert it to DTO
        return modelMapper.map(entity, TransactionDTO.class);
    }

    public Transaction convertToEntity(TransactionDTO dto) {
        //this method will accept dto and convert it to entity
        return modelMapper.map(dto, Transaction.class);
    }

}
