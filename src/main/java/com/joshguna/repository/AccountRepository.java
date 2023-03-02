package com.joshguna.repository;

import com.joshguna.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {

    //Group of accounts
    public static List<Account> accountList = new ArrayList<>();

    //Adding new account to the group
    public Account save(Account account) {
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountList;
    }
}
