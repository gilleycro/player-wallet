package com.example.playerwallet.manager;

import com.example.playerwallet.dto.PlayerBalanceDTO;
import com.example.playerwallet.model.Transaction;
import com.example.playerwallet.service.AccountService;
import com.example.playerwallet.service.PlayerService;
import com.example.playerwallet.service.TransactionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryManager {

    private AccountService accountService;
    private TransactionService transactionService;
    private PlayerService playerService;

    public HistoryManager(AccountService accountService, TransactionService transactionService, PlayerService playerService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.playerService = playerService;
    }

    public List<Transaction> getHistoryForUser(String username){
        var playerId = playerService.getPlayerIdByUsername(username);
        var accountId = accountService.findAccountIdByPlayerId(playerId.get());
        return transactionService.findAllTransactionForAccountId(accountId);
    }

    public List<Transaction> getHistoryForUser(Long id){
        var accountId = accountService.findAccountIdByPlayerId(id);
        return transactionService.findAllTransactionForAccountId(accountId);
    }

    public List<PlayerBalanceDTO> getPlayerBalances(){
        return accountService.getPlayerBalances();
    }

}
