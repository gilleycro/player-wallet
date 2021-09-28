package com.example.playerwallet.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepositoryCustom {

    @Query("select p.id from Player p where p.username = ?1")
    Optional<Long> findByUsername(String username);
}
