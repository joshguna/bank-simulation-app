package com.joshguna.model;

import com.joshguna.enums.AccountStatus;
import com.joshguna.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Account {

    private UUID id;
    private AccountStatus accountStatus;
    private Date creationDate;

    @NotNull
    @Positive
    private BigDecimal balance;

    @NotNull
    private AccountType accountType;

    @NotNull
    private Long userId;

}
