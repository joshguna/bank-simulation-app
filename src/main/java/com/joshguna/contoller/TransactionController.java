package com.joshguna.contoller;

import com.joshguna.model.Transaction;
import com.joshguna.service.AccountService;
import com.joshguna.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    AccountService accountService;
    TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model) {

        //what we need to provide to make transfer happen
        //we need to provide empty transaction object
        model.addAttribute("transaction", Transaction.builder().build());

        //we need all accounts to provide them as sender, receiver
        model.addAttribute("accounts", accountService.listAllAccount());

        //we need list of transactions(last 10) to fill table.(business logic is missing)
        model.addAttribute("lastTransactions", transactionService.last10Transactions());

        return "transaction/make-transfer";
    }


}