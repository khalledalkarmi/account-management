package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import event.eventusecases.ChangeStatusEventUseCase;
import event.eventusecases.CreateAccountEventUseCase;
import model.Account;
import org.springframework.stereotype.Repository;
import repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryMySQL implements AccountRepository {
    private final JpaAccountRepository jpaAccountRepository;

    private final AccountMapper mapper;



    public AccountRepositoryMySQL(JpaAccountRepository jpaAccountRepository, AccountMapper mapper) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Account account) {
        jpaAccountRepository.save(mapper.map(account));
    }


    @Override
    public List<Account> findAll() {
        List<AccountEntity> all = jpaAccountRepository.findAll();
        return all.stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public Account findByAccountNumber(String id) {
        Optional<AccountEntity> accountEntity = jpaAccountRepository.findByAccountNumber(Long.parseLong(id));
        return accountEntity.map(mapper::map).orElse(null);
    }
}
