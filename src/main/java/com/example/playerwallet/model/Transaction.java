package com.example.playerwallet.model;

import com.example.playerwallet.model.enums.TransactionType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @Column(name = "transaction_id", unique = true)
    private Long transactionId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    //todo: fix column name
    @Column(name = "account_id")
    private Long accountId;

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Integer version;


    public Transaction(Long transactionId, double amount, TransactionType transactionType, Long accountId) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.accountId = accountId;
    }
}
