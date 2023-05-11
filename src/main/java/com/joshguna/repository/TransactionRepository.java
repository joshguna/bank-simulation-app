package com.joshguna.repository;

import com.joshguna.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();
//
//    public TransactionDTO save(TransactionDTO transactionDTO) {
//        transactionDTOList.add(transactionDTO);
//        return transactionDTO;
//    }
//
//    public List<TransactionDTO> findAll() {
//        return transactionDTOList;
//    }
//
//
//    public List<TransactionDTO> findLast10Transactions() {
//        return transactionDTOList.stream()
//                .sorted(Comparator.comparing(TransactionDTO::getCreationDate).reversed())
//                .limit(10).collect(Collectors.toList());
//    }
//
//    public List<TransactionDTO> findTransactionListById(Long id) {
//
//        //this method finds all the money one account received or sent
//
//        return transactionDTOList.stream()
//                .filter(transactionDTO -> transactionDTO.getSender().equals(id)
//                        || transactionDTO.getReceiver().equals(id)).collect(Collectors.toList());
//    }
}
