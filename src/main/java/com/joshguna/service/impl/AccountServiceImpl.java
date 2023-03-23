package com.joshguna.service.impl;

import com.joshguna.enums.AccountStatus;
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

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        //1 - we need to create account object
        //this is lombok build method, we could use setter or new keyword
        Account account = Account.builder().id(UUID.randomUUID())
                .userId(userId).balance(balance)
                .accountType(accountType).creationDate(creationDate)
                .accountStatus(AccountStatus.ACTIVE).build();

        //2 - save into the db (repository)
        return accountRepository.save(account);

    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(UUID id) {
        accountRepository.findAll().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst().get()
                .setAccountStatus(AccountStatus.DELETED);
    }
}
