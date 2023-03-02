package com.joshguna.service;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationDate,
                             AccountType accountType, Long userID);

    List<Account> listAllAccounts();

}
