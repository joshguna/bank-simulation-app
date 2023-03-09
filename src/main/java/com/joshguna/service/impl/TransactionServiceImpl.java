package com.joshguna.service.impl;

import com.joshguna.enums.AccountType;
import com.joshguna.exception.AccountOwnershipException;
import com.joshguna.exception.BadRequestException;
import com.joshguna.model.Account;
import com.joshguna.model.Transaction;
import com.joshguna.repository.AccountRepository;
import com.joshguna.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*
            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance ?
            -if both accounts are checking, if not, one of them saving, it needs to be same userId
         */
        validateAccount(sender, receiver);

        return null;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {

        //if statement that checks if one of the account is saving,
        //and user of sender or receiver is not the same, throw AccountOwnershipException

        if ((sender.getAccountType().equals(AccountType.SAVING) ||
                receiver.getAccountType().equals(AccountType.SAVING)) && !(sender.getUserId().equals(receiver.getUserId()))) {
            throw new AccountOwnershipException("Accounts cannot be savings, or sender and receiver cannot be same");
        }
    }

    private void validateAccount(Account sender, Account receiver) {
        /*
            -if any of the account is null
            -if account ids are the same(same account)
            -if the accounts exist in the database(repository)
         */

        if (sender == null || receiver == null) {
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        //verify if we have sender and receiver in the database
        findAccountById(sender.getId());
        findAccountById(receiver.getId());


    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
