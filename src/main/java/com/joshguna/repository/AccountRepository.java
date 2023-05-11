package com.joshguna.repository;

import com.joshguna.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    public static List<AccountDTO> accountDTOList = new ArrayList<>();
//
//    public AccountDTO save(AccountDTO accountDTO) {
//        accountDTOList.add(accountDTO);
//        return accountDTO;
//    }
//
//    public List<AccountDTO> findAll() {
//        return accountDTOList;
//    }
//
//    public AccountDTO findById(Long id) {
//        //Method, that finds the account inside the list, if not
//        //throws RecordNotFoundException
//
//        return accountDTOList.stream().filter(account -> account.getId().equals(id))
//                .findAny().orElseThrow(() -> new RecordNotFoundException("AccountDTO not exist in the database."));
//
//    }
}
