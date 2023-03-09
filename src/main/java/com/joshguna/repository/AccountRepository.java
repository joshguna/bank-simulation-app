package com.joshguna.repository;

import com.joshguna.exception.RecordNotFoundException;
import com.joshguna.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<Account> accountList = new ArrayList<>();

    public Account save(Account account) {
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountList;
    }

    public Account findById(UUID id) {
        //Method, that finds the account inside the list, if not
        //throws RecordNotFoundException

        return accountList.stream().filter(account -> account.getId().equals(id))
                .findAny().orElseThrow(() -> new RecordNotFoundException("Account not exist in the database."));

    }
}
