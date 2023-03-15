package com.joshguna;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;
import com.joshguna.repository.AccountRepository;
import com.joshguna.service.AccountService;
import com.joshguna.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationApp {

    public static void main(String[] args) {
        ApplicationContext container = SpringApplication.run(BankSimulationApp.class, args);




        /*
        **
        * Code block below is used for debugging and logging info to console
        *

        //getting account nd transaction service beans
        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);

        //create 2 accounts: sender and receiver
        Account sender = accountService.createNewAccount(BigDecimal.valueOf(70), new Date(), AccountType.CHECKING, 1L);

        Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.SAVING, 1L);

        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransfer(sender, receiver, new BigDecimal(40), new Date(), "Transaction 1");

        System.out.println(transactionService.findAllTransaction().get(0));

        accountService.listAllAccount().forEach(System.out::println);
         */


    }


}
