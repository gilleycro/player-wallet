package com.example.playerwallet.service;

import com.example.playerwallet.dto.TransactionDTO;
import com.example.playerwallet.exception.TransactionNotUniqueException;
import com.example.playerwallet.model.Transaction;
import com.example.playerwallet.model.enums.TransactionType;
import com.example.playerwallet.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public void processTransaction(Long accountId, TransactionDTO transactionDTO) throws TransactionNotUniqueException {
        if(!doesTransactionIdExist(transactionDTO.getTransactionId())) {
            var transaction = new Transaction(transactionDTO.getTransactionId(),
                    transactionDTO.getAmount(),
                    transactionDTO.getTransactionType(),
                    accountId
            );
            transactionRepository.save(transaction);
        } else{
            throw new TransactionNotUniqueException("Transaction Id must be unique");
        }
    }

    @Override
    public boolean doesTransactionIdExist(Long transactionId) {
        var id = transactionRepository.findById(transactionId);
        if(id.isPresent())
            return true;
        return false;
    }

    @Override
    public List<Transaction> findAllTransactionForAccountId(Long accountId) {
       return transactionRepository.getAllTransactionForAccount(accountId);
    }
}
