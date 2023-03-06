package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountEntity;
import com.progressoft.application.entity.AccountMapper;
import model.Account;
import model.Status;
import org.springframework.stereotype.Repository;
import usecases.CreateAccountUseCase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryMySQL implements repository.AccountRepository {
    private final AccountRepository accountRepository;

    private final AccountMapper mapper;

    public AccountRepositoryMySQL(AccountRepository accountRepository,AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(mapper.map(account));
    }

    @Override
    public void deActive(Account account) {
        Optional<AccountEntity> byId = accountRepository.findById(account.getId());
        byId.ifPresent(account1 -> {
            account1.setStatus(Status.Inactive);
            accountRepository.save(mapper.map(account));
        });
    }

    @Override
    public void inActive(Account account) {
        Optional<AccountEntity> byId = accountRepository.findById(account.getId());
        byId.ifPresent(account1 -> {
            account1.setStatus(Status.Active);
            accountRepository.save(mapper.map(account));
        });
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> all = accountRepository.findAll();
        return all.stream().map(mapper::map).collect(Collectors.toList());
    }

}
