package com.joshguna.converter;

import com.joshguna.dto.AccountDTO;
import com.joshguna.service.AccountService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements Converter<String, AccountDTO> {

    private final AccountService accountService;

    public AccountConverter(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
        This method takes String as source,
        uses retrieveById method to find corresponding info in db
        then converts into DTO
     */

    @Override
    public AccountDTO convert(String source) {
        if (source == null || source.equals("")) {
            return null;
        }
        return accountService.retrieveById(Long.parseLong(source));
    }
}