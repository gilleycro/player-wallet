package com.example.playerwallet.service;

import com.example.playerwallet.dto.AccountDTO;
import com.example.playerwallet.dto.PlayerBalanceDTO;
import com.example.playerwallet.model.Account;
import com.example.playerwallet.model.Transaction;

import java.util.List;

public interface AccountService {

     List<AccountDTO> findAll();
     long findAccountIdByPlayerId(long id);
     void updateBalance(long id, double amount);
     double getCurrentBalance(long id);
     List<PlayerBalanceDTO> getPlayerBalances();
}
