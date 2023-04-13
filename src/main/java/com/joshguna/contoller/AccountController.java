package com.joshguna.contoller;

import com.joshguna.enums.AccountType;
import com.joshguna.model.Account;
import com.joshguna.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

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
        model.addAttribute("account", Account.builder().build());

        //account type enum needs to fill dropdown
        model.addAttribute("accountTypeList", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/add-user")
    public String createAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("accountTypes", AccountType.values());
        }

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

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") UUID id) {

        accountService.activateAccount(id);

        return "redirect:/index";
    }
}
