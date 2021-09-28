package com.example.playerwallet.manager;

import com.example.playerwallet.dto.TransactionDTO;
import com.example.playerwallet.exception.InsufficientFundsException;
import com.example.playerwallet.exception.TransactionNotUniqueException;
import com.example.playerwallet.model.enums.TransactionType;
import com.example.playerwallet.service.AccountService;
import com.example.playerwallet.service.TransactionService;
import org.hibernate.StaleStateException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class PaymentManager {

    private AccountService accountService;
    private TransactionService transactionService;

    public PaymentManager(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Retryable(value = StaleStateException.class,
            maxAttempts = 2, backoff = @Backoff(delay = 100))
    @Transactional
    public void processTransaction(Long id, TransactionDTO transactionDTO) throws InsufficientFundsException, TransactionNotUniqueException {
        var accountId = accountService.findAccountIdByPlayerId(id);
        var currentSaldo = accountService.getCurrentBalance(accountId);
        var newSaldo = calculateUpdateAmount(currentSaldo, transactionDTO.getAmount(), transactionDTO.getTransactionType());

        if (canPaymentBeProcessed(transactionDTO, newSaldo)) {
            transactionService.processTransaction(accountId, transactionDTO);
            accountService.updateBalance(accountId, newSaldo);
        }
    }

    private double calculateUpdateAmount(double currentSaldo, double amount, TransactionType transactionType) {
        if (transactionType == TransactionType.DEBIT)
            amount *= -1;
        return currentSaldo + amount;
    }

    private boolean canPaymentBeProcessed(TransactionDTO transactionDTO, double newSaldo) throws InsufficientFundsException {
        return canTransactionProceed(transactionDTO.getTransactionType(),newSaldo);
    }

    private boolean canTransactionProceed(TransactionType transactionType, double newSaldo) throws InsufficientFundsException {
        if (transactionType == TransactionType.CREDIT) {
            return true;
        } else if (transactionType == TransactionType.DEBIT && newSaldo >= 0) {
            return true;
        } else {
            throw new InsufficientFundsException("There is not enough funds to process debit transaction");
        }
    }

}
