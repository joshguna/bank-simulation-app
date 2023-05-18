package com.joshguna.contoller;

import com.joshguna.dto.AccountDTO;
import com.joshguna.dto.TransactionDTO;
import com.joshguna.service.AccountService;
import com.joshguna.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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

        //what we need to provide to make transfer happen`
        //we need to provide empty transaction object
        model.addAttribute("transaction", new TransactionDTO());

        //we need all accounts to provide them as sender, receiver
        model.addAttribute("accounts", accountService.listAllAccount());

        //we need list of transactions(last 10) to fill table.(business logic is missing)
        model.addAttribute("lastTransactions", transactionService.last10Transactions());

        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String postMakeTransfer(@Valid @ModelAttribute("transaction") TransactionDTO transactionDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("accounts", accountService.listAllAccount());
            return "transaction/make-transfer";
        }

        //make transfer method of transactionDTO requires AccountDTO obj
        //we can find UUID using account service
        //method needed to retrieve account based on ID

        AccountDTO sender = accountService.retrieveById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.retrieveById(transactionDTO.getReceiver().getId());

        transactionService.makeTransfer(sender, receiver, transactionDTO.getAmount(), new Date(), transactionDTO.getMessage());

        return "redirect:/make-transfer";
    }

    @GetMapping("/transaction/{id}")
    public String getTransactionList(@PathVariable("id") Long id, Model model) {

        System.out.println(id);

        model.addAttribute("transactions", transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }

}