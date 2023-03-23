package com.joshguna.contoller;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;
import com.joshguna.repository.AccountRepository;
import com.joshguna.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/index")
    public String indexPage(Model model) {

        //This is also possible
        // List<Account> listOfAccount = new ArrayList<>();
        // listOfAccount.addAll(accountService.listAllAccount());

        model.addAttribute("accountList", accountService.listAllAccount());

        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model) {

        //empty account object provided
        model.addAttribute("account", new Account());

        //account type enum needs to fill dropdown
        model.addAttribute("accountTypeList", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/add-user")
    public String createAccount(@ModelAttribute("account") Account account) {

        //Model attribute captures information from UI
        //print them on the console.
        System.out.println(account.toString());

        //Save UI data into DB
        accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());

//        return "account/index";
        return "redirect:/index";


    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id) {


        accountService.deleteAccount(id);
        System.out.println(id);

        return "redirect:/index";


    }

}
