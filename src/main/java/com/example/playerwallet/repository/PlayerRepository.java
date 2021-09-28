package com.example.playerwallet.repository;

import com.example.playerwallet.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryCustom {
}
