package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import model.Account;
import model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryMySQL implements repository.AccountRepository {
    private final AccountRepository accountRepository;

    private final AccountMapper mapper;

    public AccountRepositoryMySQL(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(mapper.map(account));
    }

    @Override
    public Status deActive(String id) {
        Account account = findByID(id);
        account.setStatus(Status.Inactive);
        save(account);
        return account.getStatus();
    }

    @Override
    public Status inActive(String id) {
        Account account = findByID(id);
        account.setStatus(Status.Active);
        save(account);
        return account.getStatus();
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> all = accountRepository.findAll();
        return all.stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public Account findByID(String id) {
        Optional<AccountEntity> accountEntity = accountRepository.findByCustomerId(id);
        return accountEntity.map(mapper::map).orElse(null);
    }

}
