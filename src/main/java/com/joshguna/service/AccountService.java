package com.joshguna.service;

import com.joshguna.dto.AccountDTO;
import com.joshguna.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccount();

    public void deleteAccount(Long id);

    void activateAccount(Long id);

    AccountDTO retrieveByID(Long id);
}
