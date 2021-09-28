package com.example.playerwallet.service;

import java.util.Optional;

public interface PlayerService {
    Optional<Long> getPlayerIdByUsername(String username);
}
