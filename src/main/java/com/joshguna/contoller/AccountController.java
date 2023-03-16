package com.joshguna.contoller;

import com.joshguna.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/index")
    public String indexPage(Model model) {

        //This is also possible
        // List<Account> listOfAccount = new ArrayList<>();
        // listOfAccount.addAll(accountService.listAllAccount());

        model.addAttribute("accountList", accountService.listAllAccount());

        return "account/index";
    }

}
