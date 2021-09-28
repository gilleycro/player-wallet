package com.example.playerwallet.controller;

import com.example.playerwallet.dto.PlayerBalanceDTO;
import com.example.playerwallet.dto.TransactionDTO;
import com.example.playerwallet.exception.InsufficientFundsException;
import com.example.playerwallet.exception.TransactionNotUniqueException;
import com.example.playerwallet.manager.HistoryManager;
import com.example.playerwallet.manager.PaymentManager;
import com.example.playerwallet.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private HistoryManager historyManager;

    @Autowired
    PaymentManager paymentManager;

    @GetMapping("/transaction-username")
    public List<Transaction> getTransactionHistoryForPlayer(@RequestParam(name = "username") String username) {
        return historyManager.getHistoryForUser(username);
    }

    @GetMapping("/transaction-id")
    public List<Transaction> getTransactionHistoryForPlayer(@RequestParam(name = "playerId") Long id) {
        return historyManager.getHistoryForUser(id);
    }

    @GetMapping("/balance")
    public List<PlayerBalanceDTO> getBalanceForPlayer() {
        return historyManager.getPlayerBalances();
    }

    @PostMapping("/process-payment/{id}")
    public ResponseEntity processTransaction(
            @PathVariable Long id,
            @RequestBody TransactionDTO transaction) {
        try {
            paymentManager.processTransaction(id, transaction);
            return ResponseEntity
                    .status(HttpStatus.OK).body(null);
        } catch (TransactionNotUniqueException | InsufficientFundsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
