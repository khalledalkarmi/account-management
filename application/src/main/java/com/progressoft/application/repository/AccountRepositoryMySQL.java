package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountMapper;
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
        jpaAccountRepository.save(mapper.toAccountEntity(account));
    }

    @Override
    public List<Account> findAll() {
        return jpaAccountRepository.findAll().stream()
                .map(mapper::toAccount)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findByAccountNumberAndCustomerId(String id, String customerId) {
        return jpaAccountRepository.findByAccountNumberAndCustomerId(Long.parseLong(id), customerId)
                .map(mapper::toAccount);
    }
}
