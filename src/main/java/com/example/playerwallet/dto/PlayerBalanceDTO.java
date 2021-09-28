package com.example.playerwallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PlayerBalanceDTO {

    public PlayerBalanceDTO(String username, double saldo) {
        this.username = username;
        this.saldo = saldo;
    }

    private String username;
    private double saldo;

}
