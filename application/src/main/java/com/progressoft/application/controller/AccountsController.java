package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import model.Account;
import model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import usecases.CreateAccountUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AccountsController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final CreateAccountUseCase createAccountUseCase;

    private final AccountRepositoryMySQL accountRepository;
    private final AccountMapper accountMapper;

    public AccountsController(CreateAccountUseCase createAccountUseCase, AccountRepositoryMySQL accountRepository, AccountMapper accountMapper) {
        this.createAccountUseCase = createAccountUseCase;
        this.accountRepository = accountRepository;

        this.accountMapper = accountMapper;
    }

    @GetMapping("/accounts")
    public List<Account> sayHello() {
        List<Account> all = accountRepository.findAll();
        all.forEach(System.out::println);

        return all ;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody AccountEntity accountEntity){
        Account map = accountMapper.map(accountEntity);
        System.out.println(accountEntity);
        createAccountUseCase.execute(map);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
