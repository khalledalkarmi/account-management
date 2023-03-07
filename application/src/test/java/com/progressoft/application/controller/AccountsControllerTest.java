package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import model.Account;
import model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import usecases.CreateAccountUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(value = AccountsController.class)
@ExtendWith(SpringExtension.class)
class AccountsControllerTest {

    @MockBean
    private AccountRepositoryMySQL accountRepository;
    @MockBean
    private AccountMapper accountMapper;
    @MockBean
    private CreateAccountUseCase createAccountUseCase;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllAccounts() throws Exception {
        Account account = Account.builder()
                .id("KHALEDKAR")
                .creationDate(LocalDateTime.now())
                .status(Status.Inactive)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();
        when(accountRepository.findAll()).thenReturn(List.of(account));
        RequestBuilder request = MockMvcRequestBuilders.get("/accounts").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertThat(HttpStatus.OK.value()).isEqualTo(response.getStatus());
    }

    @Test
    public void givenInvalidAccount_whenAddAccount_thenExpectedStatusCode() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        Account account = Account.builder()
                .id("KHALEDKAR")
                .creationDate(now)
                .status(Status.Inactive)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();

        doNothing().when(createAccountUseCase).execute(account);
        doNothing().when(accountRepository).save(account);

        String json = "{\"customerId\":\"KHALEDKAR\",\"creationDate\" : \" " + now + "\", \"status\":\"Inactive\",\"availableBalance\":\"3025.5015\",\"accountNumber\":\"123456\"}";
        RequestBuilder request = MockMvcRequestBuilders
                .post("/add")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertThat(HttpStatus.BAD_REQUEST.value()).isEqualTo(response.getStatus());
        Assertions.assertThat(response.getContentAsString()).isEqualTo("Customer Not Exist");

    }


    @Test
    public void givenValidAccount_whenAddAccount_thenExpectedStatusCode() throws Exception {

        LocalDateTime now = LocalDateTime.now();
        Account account = Account.builder()
                .id("KHALEDKAR")
                .creationDate(now)
                .status(Status.Inactive)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();

//        doNothing().when(accountRepository).save(account);
//        doNothing().when(createAccountUseCase).execute(account);

        String json = "{\"id\":\"KHALEDKAR\",\"creationDate\":\"" + now + "\",\"status\":\"Inactive\",\"availableBalance\":\"3025.5015\",\"accountNumber\":\"123456\"}";

        RequestBuilder request = MockMvcRequestBuilders
                .post("/add")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        Assertions.assertThat("http://localhost/add").isEqualTo(
                response.getHeader(HttpHeaders.LOCATION));
    }

    //TODO: check duplicated Account when create

    @Test
    public void givenValidAccountID_whenDeActive_thenExpectedStatusCode() throws Exception {
        Account account = Account.builder()
                .id("KHALEDKAR")
                .creationDate(LocalDateTime.now())
                .status(Status.Inactive)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();
        when(accountRepository.deActive(anyString())).thenReturn(Status.Inactive);
        when(accountRepository.findByID(anyString())).thenReturn(account);

        String jsonId = "\"id\":\"KHALEDKAR\"";
        RequestBuilder request = MockMvcRequestBuilders
                .post("/deactivate")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonId)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request).andReturn();

        MockHttpServletResponse response = result.getResponse();


        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        Assertions.assertThat("http://localhost/deactivate").isEqualTo(
                response.getHeader(HttpHeaders.LOCATION));
    }

}