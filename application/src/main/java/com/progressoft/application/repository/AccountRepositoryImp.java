package com.progressoft.application.repository;

import model.Account;
import model.Status;

import java.util.Optional;

public class AccountRepositoryImp implements repository.AccountRepository {
    private final AccountRepository accountRepository;

    public AccountRepositoryImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deActive(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        byId.ifPresent(account1 -> {
            account1.setStatus(Status.Inactive);
            accountRepository.save(account);
        });
    }

    @Override
    public void inActive(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        byId.ifPresent(account1 -> {
            account1.setStatus(Status.Active);
            accountRepository.save(account);
        });
    }
}
