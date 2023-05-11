package com.joshguna.service.impl;

import com.joshguna.dto.AccountDTO;
import com.joshguna.entity.Account;
import com.joshguna.enums.AccountStatus;
import com.joshguna.enums.AccountType;
import com.joshguna.mapper.AccountMapper;
import com.joshguna.repository.AccountRepository;
import com.joshguna.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        //we will complete the DTO
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO.setCreationDate(new Date());

        //convert it to entity and save it.
        accountRepository.save(accountMapper.convertToEntity(accountDTO));
    }

    @Override
    public List<AccountDTO> listAllAccount() {

        /*
            we are getting list of account from repo(database
            but we need to return list of AccountDTO to controller
            what we need to do is we will convert Accounts to AccountsDTO
         */

        List<Account> accountList = accountRepository.findAll();
        //we are converting list of account to accountDTOs and returning it.
        return accountList.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findAll().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst().get()
                .setAccountStatus(AccountStatus.DELETED);
    }

    @Override
    public void activateAccount(Long id) {
        accountRepository.findAll().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst().get()
                .setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public AccountDTO retrieveByID(Long id) {
        return accountRepository.findById(id);
    }
}
