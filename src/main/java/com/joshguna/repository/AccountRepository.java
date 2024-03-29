package com.joshguna.repository;

import com.joshguna.exception.RecordNotFoundException;
import com.joshguna.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<AccountDTO> accountDTOList = new ArrayList<>();

    public AccountDTO save(AccountDTO accountDTO) {
        accountDTOList.add(accountDTO);
        return accountDTO;
    }

    public List<AccountDTO> findAll() {
        return accountDTOList;
    }

    public AccountDTO findById(UUID id) {
        //Method, that finds the account inside the list, if not
        //throws RecordNotFoundException

        return accountDTOList.stream().filter(account -> account.getId().equals(id))
                .findAny().orElseThrow(() -> new RecordNotFoundException("AccountDTO not exist in the database."));

    }
}
