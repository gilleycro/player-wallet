package com.example.playerwallet.service;

import com.example.playerwallet.dto.AccountDTO;
import com.example.playerwallet.dto.PlayerBalanceDTO;
import com.example.playerwallet.model.Account;
import com.example.playerwallet.model.Transaction;
import com.example.playerwallet.model.enums.TransactionType;
import com.example.playerwallet.repository.AccountRepository;
import com.example.playerwallet.repository.AccountRepositoryCustom;
import com.example.playerwallet.repository.TransactionRepository;
import org.hibernate.StaleStateException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream()
                .map(acc -> new AccountDTO(acc.getAccountId(), acc.getSaldo()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateBalance(long id, double amount) {
        accountRepository.updateAccountBalance(amount, id);
    }

    @Override
    public double getCurrentBalance(long id) {
        return accountRepository.getCurrentBalance(id);
    }

    @Override
    public long findAccountIdByPlayerId(long id) {
        return accountRepository.findAccountIdByPlayerId(id);
    }

    @Override
    public List<PlayerBalanceDTO> getPlayerBalances() {
        return accountRepository.getPlayersBalance().stream()
                .map( data -> new PlayerBalanceDTO(data.get(0, String.class), data.get(1, Double.class)))
                .collect(Collectors.toList());
    }
}
