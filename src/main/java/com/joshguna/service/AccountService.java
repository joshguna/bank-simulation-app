package com.joshguna.service;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();

    public void deleteAccount(UUID id);

    void activateAccount(UUID id);
}
