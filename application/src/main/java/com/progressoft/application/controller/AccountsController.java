package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.resources.AccountRequest;
import com.progressoft.application.resources.AccountResponse;
import exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import repository.AccountRepository;
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
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final InactivateAccountUseCase inactivateAccountUseCase;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<AccountResponse> getAllAccount() {
        log.info("Get all account");
        return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AccountRequest accountRequest) {
        Account map = accountMapper.toAccount(accountRequest);
        log.info("Create account  Customer ID {}", accountRequest.getCustomerId());
        createAccountUseCase.execute(map);
    }

    @PostMapping("{customerId}/{accountNumber}/deactivate")
    @CrossOrigin(origins = "http://localhost:4200")
    public void deactivate(@PathVariable String accountNumber, @PathVariable String customerId) {
        //TODO A bug will occur if the account is null

        Account account = accountRepository.findByAccountNumberAndCustomerId(accountNumber, customerId)
                .orElseThrow(UserNotFoundException::new);
        log.info("Deactivate Account number {}, Customer ID {}", account.getAccountNumber(), account.getCustomerId());
        deactivateAccountUseCase.execute(account);
    }

    @PostMapping("{customerId}/{accountNumber}/activate")
    @CrossOrigin(origins = "http://localhost:4200")
    public void activate(@PathVariable String accountNumber, @PathVariable String customerId) {
        //TODO A bug will occur if the account is null

        Account account = accountRepository.findByAccountNumberAndCustomerId(accountNumber, customerId)
                .orElseThrow(UserNotFoundException::new);
        log.info("Deactivate Account number {}, Customer ID {}", account.getAccountNumber(), account.getCustomerId());
        inactivateAccountUseCase.execute(account);
    }

    @ResponseBody
    @GetMapping(value = "{customerId}/{accountNumber}", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:4200")
    public AccountResponse getAccountByCustomerIdAndAccountNumber(@PathVariable String accountNumber, @PathVariable String customerId) {
        Account account = accountRepository.findByAccountNumberAndCustomerId(accountNumber, customerId)
                .orElseThrow(UserNotFoundException::new);
        log.info("Get Account by Account number {} and Customer ID {}", account.getAccountNumber(), account.getCustomerId());
        return accountMapper.toAccountResponse(account);
    }
}
