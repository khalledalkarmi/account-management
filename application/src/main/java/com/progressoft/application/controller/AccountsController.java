package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import com.progressoft.application.resources.AccountRequest;
import com.progressoft.application.resources.AccountResponse;
import event.eventusecases.ChangeStatusEventUseCase;
import event.eventusecases.CreateAccountEventUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;
import usecases.InactivateAccountUseCase;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountsController {

    //DONE Controllers or REST endpoints should not expose Entities or Domain models, Create resources/requests for them

    //DONE Use proper REST Conventions for URLs and HTTP Methods

    //TODO Lookup @RestControllerAdvice to handle exceptions and return proper HTTP Statuses

    // api/v1/accounts/createNewAccount X bad practice
    // /add X bad practice

    // /api/v1/accounts Post creates a new account
    // /api/v1/accounts?status=ACTIVE Get lists all accounts
    // /api/v1/accounts/activeAccounts Get lists all accounts
    // /api/v1/accounts/{customerId}/{accountNumber}

    private final CreateAccountUseCase createAccountUseCase;
    private final DeactivateAccountUseCase deactivateAccountUseCase;
    private final AccountRepositoryMySQL accountRepository;
    private final AccountMapper accountMapper;
    private final InactivateAccountUseCase inactivateAccountUseCase;


    @GetMapping
    public List<AccountResponse> getAllAccount() {
        return accountRepository.findAll().stream().map(accountMapper::mapEntity).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AccountRequest accountRequest) {
        Account map = accountMapper.map(accountRequest);
        log.info("Received create account request {}", accountRequest);
        createAccountUseCase.execute(map);
    }

    @PostMapping("{accountNumber}/deactivate")
    public void deactivate(@PathVariable String accountNumber) {
        //TODO A bug will occur if the account is null

        Account account = accountRepository.findByAccountNumber(accountNumber);
        log.info("Deactivate Account number {}", account);
        deactivateAccountUseCase.execute(account);
    }

    @PostMapping("{accountNumber}/activate")
    public void activate(@PathVariable String accountNumber) {
        //TODO A bug will occur if the account is null

        Account account = accountRepository.findByAccountNumber(accountNumber);
        log.info("Inactivate Account number {}", account.getAccountNumber());
        inactivateAccountUseCase.execute(account);
    }
}
