package com.joshguna.contoller;

import com.joshguna.dto.AccountDTO;
import com.joshguna.enums.AccountType;
import com.joshguna.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String indexPage(Model model) {

        //This is also possible
        // List<AccountDTO> listOfAccount = new ArrayList<>();
        // listOfAccount.addAll(accountService.listAllAccount());

        model.addAttribute("accountList", accountService.listAllAccount());

        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model) {

        //empty account object provided
        model.addAttribute("account", new AccountDTO());

        //account type enum needs to fill dropdown
        model.addAttribute("accountTypeList", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") AccountDTO accountDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("accountTypeList", AccountType.values());
            return "account/create-account";
        }

        //Model attribute captures information from UI
        //print them on the console.
        System.out.println(accountDTO.toString());

        //Save UI data into DB
        accountService.createNewAccount(accountDTO.getBalance(), new Date(), accountDTO.getAccountType(), accountDTO.getUserId());

//        return "accountDTO/index";
        return "redirect:/index";


    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {


        accountService.deleteAccount(id);
        System.out.println(id);

        return "redirect:/index";


    }

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") Long id) {

        accountService.activateAccount(id);

        return "redirect:/index";
    }
}
