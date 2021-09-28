package com.example.playerwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class PlayerWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerWalletApplication.class, args);
    }

}
