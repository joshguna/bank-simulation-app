package com.joshguna.contoller;

import com.joshguna.model.Account;
import com.joshguna.model.Transaction;
import com.joshguna.service.AccountService;
import com.joshguna.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

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

    @PostMapping("/transfer")
    public String postMakeTransfer(@ModelAttribute("transaction") Transaction transaction) {

        //make transfer method of transaction requires Account obj
        //we can find UUID using account service
        //method needed to retrieve account based on ID

        Account sender = accountService.retrieveByID(transaction.getSender());
        Account receiver = accountService.retrieveByID(transaction.getReceiver());

        transactionService.makeTransfer(sender, receiver, transaction.getAmount(), new Date(), transaction.getMessage());

        return "redirect:/make-transfer";
    }


}