package com.joshguna.service.impl;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;
import com.joshguna.repository.AccountRepository;
import com.joshguna.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    //whenever dependency injection, it's best to go with private final
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userID) {

        //1 - we need to create account object
        //this is lombok build method, we could use setter or new keyword
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
        return accountRepository.findAll();
    }
}
