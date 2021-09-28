package com.example.playerwallet.repository;

import com.example.playerwallet.model.Transaction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepositoryCustom {

    @Query("select t from Transaction t where t.accountId = ?1")
    List<Transaction> getAllTransactionForAccount(Long accountId);
}
