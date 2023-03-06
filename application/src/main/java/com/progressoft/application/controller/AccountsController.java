package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import usecases.CreateAccountUseCase;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AccountsController {
    private final AccountRepositoryMySQL accountRepository;
    private final AccountMapper accountMapper;

    public AccountsController(AccountRepositoryMySQL accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;

        this.accountMapper = accountMapper;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> all = accountRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody AccountEntity account) {
        //TODO: use as bean
        new CreateAccountUseCase(accountRepository).execute(accountMapper.map(account));
        return null;
    }
}
