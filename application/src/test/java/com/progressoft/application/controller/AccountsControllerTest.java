package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import com.progressoft.application.repository.JpaAccountRepository;
import model.Account;
import model.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import repository.AccountRepository;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;
import usecases.InactivateAccountUseCase;
import validator.CreateAccountValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AccountsController.class)
@ExtendWith(SpringExtension.class)
class AccountsControllerTest {

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private AccountRepositoryMySQL accountRepositoryMySQL;
    @MockBean
    private AccountMapper accountMapper;
    @MockBean
    private CreateAccountUseCase createAccountUseCase;
    @MockBean
    private DeactivateAccountUseCase deactivateAccountUseCase;
    @MockBean
    private InactivateAccountUseCase inactivateAccountUseCase;
    @MockBean
    private JpaAccountRepository jpaAccountRepository;

    @MockBean
    CreateAccountValidator createAccountValidator;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllAccounts() throws Exception {
        Account account = Account.builder()
                .creationDate(LocalDateTime.now())
                .status(Status.Inactive)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();
        when(accountRepository.findAll()).thenReturn(List.of(account));
        mockMvc.perform(get("/api/v1/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidAccount_whenAddAccount_thenExpectedStatusCode() throws Exception {
        String json = "{\"customerId\":\"KHALEDKAR\",\"availableBalance\":\"3025.5015\"}";

        mockMvc.perform(post("/api/v1/accounts")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    public void givenValidAccountID_whenDeActive_thenExpectedStatusCode() throws Exception {
        Optional<Account> account = Optional.ofNullable(Account.builder().customerId("KHALEDKAR").accountNumber(123456789123L).status(Status.Active).build());
        when(accountRepositoryMySQL.findByAccountNumberAndCustomerId(anyString(),anyString())).thenReturn(account);
        doNothing().when(deactivateAccountUseCase).execute(account.get());

        mockMvc.perform(post("/api/v1/accounts/KHALEDKAR/123456789123/deactivate")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}