package com.progressoft.application.controller;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import model.Account;
import model.Status;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/add")
    public void add(){
        AccountEntity accountEntity =  AccountEntity.builder()
                .accountNumber(Long.parseLong("123"))
                .status(Status.Inactive)
                .creationDate(LocalDateTime.now())
                .availableBalance(BigDecimal.ONE)
                .customerId("khaled")
                .build();
        createAccountUseCase.execute(accountMapper.map(accountEntity));
    }
}
