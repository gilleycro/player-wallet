package com.example.playerwallet;

import com.example.playerwallet.dto.TransactionDTO;
import com.example.playerwallet.exception.InsufficientFundsException;
import com.example.playerwallet.exception.TransactionNotUniqueException;
import com.example.playerwallet.manager.PaymentManager;
import com.example.playerwallet.model.enums.TransactionType;
import com.example.playerwallet.service.AccountService;
import com.example.playerwallet.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class PaymentManagerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private PaymentManager paymentManager;


    @Test
    public void testCorrectUpdateOfBalanceDEBIT() {
        var transaction = new TransactionDTO(1l, 20.0, TransactionType.DEBIT);
        try {
            when(accountService.findAccountIdByPlayerId(1l)).thenReturn(1l);
            when(accountService.getCurrentBalance(1)).thenReturn(30.0);
            paymentManager.processTransaction(1l, transaction);
            verify(accountService, times(1)).updateBalance(1l, 10.0);
        } catch (InsufficientFundsException | TransactionNotUniqueException e) {
            assertTrue(e instanceof InsufficientFundsException);
        }
    }

    @Test
    public void testCorrectUpdateOfBalanceCREDIT() {
        var transaction = new TransactionDTO(1l, 20.0, TransactionType.CREDIT);
        try {
            when(accountService.findAccountIdByPlayerId(1l)).thenReturn(1l);
            when(accountService.getCurrentBalance(1)).thenReturn(30.0);
            paymentManager.processTransaction(1l, transaction);
            verify(accountService, times(1)).updateBalance(1l, 50.0);
        } catch (InsufficientFundsException | TransactionNotUniqueException e) {
            assertTrue(e instanceof InsufficientFundsException);
        }
    }

    @Test
    public void testInsufficientFundsException() {
        var transaction = new TransactionDTO(1l, 20.0, TransactionType.DEBIT);
        when(accountService.findAccountIdByPlayerId(1l)).thenReturn(1l);
        when(accountService.getCurrentBalance(1)).thenReturn(10.0);

        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
            paymentManager.processTransaction(1l, transaction);
        });

        assertTrue(exception.getMessage().contains("There is not enough funds to process debit transaction"));
    }

}
