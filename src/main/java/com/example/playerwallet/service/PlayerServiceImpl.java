package com.example.playerwallet.service;

import com.example.playerwallet.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Long> getPlayerIdByUsername(String username) {
       return playerRepository.findByUsername(username);
    }
}
