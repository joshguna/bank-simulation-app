package com.joshguna.contoller;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;
import com.joshguna.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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
    public String createAccount(@ModelAttribute("account") Account account, Model model) {

        //create method to capture information from UI,
        //print them on the console.
        //trigger createAccount method, create the account based on user input.

        System.out.println(account.toString());
//        return "account/index";
        return "redirect:/index";


    }

}
