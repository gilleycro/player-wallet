package com.example.playerwallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    private double saldo;

    @Column(name = "player_id")
    private Long playerId;

    @JsonIgnore
    @OneToMany(mappedBy = "accountId")
    private Set<Transaction> transaction;

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Integer version;

}
