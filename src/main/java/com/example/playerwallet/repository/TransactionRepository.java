package com.example.playerwallet.repository;

import com.example.playerwallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {
}
