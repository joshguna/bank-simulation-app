package com.joshguna.service.impl;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;
import com.joshguna.repository.AccountRepository;
import com.joshguna.service.AccountService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userID) {

        //1 - we need to create account object
        Account account = Account.builder().id(UUID.randomUUID())
                .userId(userID)
                .balance(balance)
                .accountType(accountType)
                .creationDate(creationDate)
                .build();

        //2 - save into the db (repository)
        return accountRepository.save(account);

    }

    @Override
    public List<Account> listAllAccounts() {
        return null;
    }
}
