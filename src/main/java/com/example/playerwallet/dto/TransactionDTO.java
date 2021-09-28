package com.example.playerwallet.dto;

import com.example.playerwallet.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private Long transactionId;
    private Double amount;
    private TransactionType transactionType;

}
