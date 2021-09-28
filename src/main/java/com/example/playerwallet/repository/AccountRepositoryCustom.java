package com.example.playerwallet.repository;

import com.example.playerwallet.dto.PlayerBalanceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface AccountRepositoryCustom {

    @Modifying
    @Query("update Account a set a.saldo = ?1 where a.accountId = ?2")
    void updateAccountBalance(double amount,  Long userId);

    @Query("select a.saldo from Account a where a.accountId = ?1")
    double getCurrentBalance(Long userId);

    @Query("select a.accountId from Account a where a.playerId = ?1")
    long findAccountIdByPlayerId(Long userId);

    @Query("select p.username, a.saldo from Account a inner join Player p on a.playerId = p.id")
    List<Tuple> getPlayersBalance();
}
