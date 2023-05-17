package com.joshguna.repository;

import com.joshguna.entity.Account;
import com.joshguna.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByAccountStatus(AccountStatus accountStatus);

}
