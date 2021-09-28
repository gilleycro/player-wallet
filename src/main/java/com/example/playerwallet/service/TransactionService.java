package com.example.playerwallet.service;

import com.example.playerwallet.dto.TransactionDTO;
import com.example.playerwallet.exception.TransactionNotUniqueException;
import com.example.playerwallet.model.Transaction;

import java.util.List;

public interface TransactionService {
    public void processTransaction(Long accountId, TransactionDTO transactionDTO) throws TransactionNotUniqueException;
    public boolean doesTransactionIdExist(Long transactionId);
    public List<Transaction> findAllTransactionForAccountId(Long accountId);
}
