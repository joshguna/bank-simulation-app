package com.joshguna.service.impl;

import com.joshguna.dto.AccountDTO;
import com.joshguna.entity.Transaction;
import com.joshguna.enums.AccountType;
import com.joshguna.exception.AccountOwnershipException;
import com.joshguna.exception.BadRequestException;
import com.joshguna.exception.BalanceNotSufficientException;
import com.joshguna.exception.UnderConstructionException;
import com.joshguna.dto.TransactionDTO;
import com.joshguna.mapper.TransactionMapper;
import com.joshguna.repository.TransactionRepository;
import com.joshguna.service.AccountService;
import com.joshguna.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService, TransactionMapper transactionMapper) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {
        /*
            -if sender or receiver is null ?
            -if sender and receiver is the same account ?
            -if sender has enough balance ?
            -if both accounts are checking, if not, one of them saving, it needs to be same userId
         */

        if (!underConstruction) {
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);

            //after all validation and money transfer, we create TransactionDTO obj and save/return it
            TransactionDTO transactionDTO = new TransactionDTO();

            return transactionRepository.save(transactionDTO);
        } else {
            throw new UnderConstructionException("App is under construction, try later");
        }

    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if (checkSenderBalance(sender, amount)) {

            //update balances
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }

    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        //verify sender has enough balance to send
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
    }

    private void checkAccountOwnership(AccountDTO sender, AccountDTO receiver) {

        //if statement that checks if one of the account is saving,
        //and user of sender or receiver is not the same, throw AccountOwnershipException

        if ((sender.getAccountType().equals(AccountType.SAVING) ||
                receiver.getAccountType().equals(AccountType.SAVING)) && !(sender.getUserId().equals(receiver.getUserId()))) {
            throw new AccountOwnershipException("Since you are using savings account, users must be same");
        }
    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
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

    private void findAccountById(Long id) {
        accountRepository.findById(id);
    }

    @Override
    public List<TransactionDTO> findAllTransaction() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> last10Transactions() {

        //we want last 10 latest transaction
        //write a native query to get the result for last 10 transaction
        List<Transaction> last10Transactions = transactionRepository.findLast10Transactions();

        //then convert it to dto and return it
        return last10Transactions.stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findTransactionListById(Long id) {

        //write a JPQL query to retrieve list of transactions by id
        List<Transaction> transactionListById = transactionRepository.findTransactionListById(id);

        //convert it to dto and return it
        return transactionListById.stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }
}
