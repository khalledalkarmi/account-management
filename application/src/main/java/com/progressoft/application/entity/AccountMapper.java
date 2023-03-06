package com.progressoft.application.entity;

import model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    public AccountEntity map(Account account) {
        return new AccountEntity.AccountEntityBuilder()
                .customerId(account.getId())
                .accountNumber(account.getAccountNumber())
                .status(account.getStatus())
                .creationDate(account.getCreationDate())
                .availableBalance(account.getAvailableBalance())
                .build();
    }
    public Account map(AccountEntity accountEntity) {
        return new Account(accountEntity.getCustomerId(),
                accountEntity.getAccountNumber(),
                accountEntity.getAvailableBalance(),
                accountEntity.getStatus(),
                accountEntity.getCreationDate());
    }

}
