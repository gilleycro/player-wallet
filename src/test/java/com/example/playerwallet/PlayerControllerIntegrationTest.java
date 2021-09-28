package com.example.playerwallet;

import com.example.playerwallet.dto.TransactionDTO;
import com.example.playerwallet.manager.PaymentManager;
import com.example.playerwallet.model.enums.TransactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlayerControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    PaymentManager paymentManager;

    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_URL = "/api/player/process-payment/";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testProcessTransactionOK() throws Exception {
        var transaction = new TransactionDTO(100l, 20.0, TransactionType.DEBIT);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL+ "1")
                .content(objectMapper.writeValueAsString(transaction))
                .contentType("application/json;charset=UTF-8");

        ResultActions getAction = mockMvc.perform(requestBuilder);

        getAction.andExpect(status().isOk());
    }

    @Test
    public void testProcessTransactionIDMustBeUniqueError() throws Exception {
        var transaction = new TransactionDTO(1l, 20.0, TransactionType.DEBIT);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL+ "1")
                .content(objectMapper.writeValueAsString(transaction))
                .contentType("application/json;charset=UTF-8");

        ResultActions getAction = mockMvc.perform(requestBuilder);

        getAction.andExpect(status().is4xxClientError());
        assertTrue(getAction.andReturn().getResponse().getContentAsString().equals("Transaction Id must be unique"));
    }

    @Test
    public void testProcessTransactionInsufficientFundsError() throws Exception {
        var transaction = new TransactionDTO(55667788l, 20000.0, TransactionType.DEBIT);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL+ "1")
                .content(objectMapper.writeValueAsString(transaction))
                .contentType("application/json;charset=UTF-8");

        ResultActions getAction = mockMvc.perform(requestBuilder);

        getAction.andExpect(status().is4xxClientError());
        getAction.andReturn().getResponse().getContentAsString().equals("There is not enough funds to process debit transaction");
    }



}
