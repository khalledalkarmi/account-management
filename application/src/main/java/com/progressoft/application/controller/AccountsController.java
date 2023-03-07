package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import lombok.extern.slf4j.Slf4j;
import model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    //TODO Controllers or REST endpoints should not expose Entities or Domain models, Create resources/requests for them

    //TODO Use proper REST Conventions for URLs and HTTP Methods

    //TODO All sysout statements should use Logger instead

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

    public AccountsController(CreateAccountUseCase createAccountUseCase,
                              DeactivateAccountUseCase deactivateAccountUseCase,
                              AccountRepositoryMySQL accountRepository,
                              AccountMapper accountMapper) {
        this.createAccountUseCase = createAccountUseCase;
        this.deactivateAccountUseCase = deactivateAccountUseCase;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public List<Account> getAllAccount() {
        List<Account> all = accountRepository.findAll();
        all.forEach(System.out::println);

        return all;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AccountEntity accountEntity) {
        Account map = accountMapper.map(accountEntity);
        log.info("Received create account request {}", accountEntity);
        createAccountUseCase.execute(map);
    }

    @PostMapping("{accountNumber}/deactivate")
    public void deactivate(@RequestBody Account account) {
        //TODO: this should accept body request
        log.info("Deactivate Account number {}", accountMapper);
        deactivateAccountUseCase.execute(account);
    }
}
