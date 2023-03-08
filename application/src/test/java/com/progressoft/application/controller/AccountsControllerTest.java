package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import com.progressoft.application.repository.JpaAccountRepository;
import model.Account;
import model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import repository.AccountRepository;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;
import usecases.InactivateAccountUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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


        RequestBuilder request = get("/api/v1/accounts").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertThat(HttpStatus.OK.value()).isEqualTo(response.getStatus());

        mockMvc.perform(get("/api/v1/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenValidAccount_whenAddAccount_thenExpectedStatusCode() throws Exception {
        String json = "{\"customerId\":\"KHALEDKAR\",\"availableBalance\":\"3025.5015\"}";

        RequestBuilder request = post("/api/v1/accounts")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void givenValidAccountID_whenDeActive_thenExpectedStatusCode() throws Exception {
        Account account = Account.builder().accountNumber(123456789123L).status(Status.Active).build();
        doNothing().when(deactivateAccountUseCase).execute(account);
        when(accountRepository.findByAccountNumber(anyString())).thenReturn(account);
        RequestBuilder request = post("/api/v1/accounts/123456789123/deactivate")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}