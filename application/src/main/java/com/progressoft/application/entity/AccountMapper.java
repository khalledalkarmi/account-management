package com.progressoft.application.entity;

import com.progressoft.application.resources.AccountRequest;
import com.progressoft.application.resources.AccountResponse;
import model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    public AccountEntity map(Account account) {
        return new AccountEntity.AccountEntityBuilder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .accountNumber(account.getAccountNumber())
                .status(account.getStatus())
                .creationDate(account.getCreationDate())
                .availableBalance(account.getAvailableBalance())
                .build();
    }

    public Account map(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .customerId(accountEntity.getCustomerId())
                .accountNumber(accountEntity.getAccountNumber())
                .availableBalance(accountEntity.getAvailableBalance())
                .status(accountEntity.getStatus())
                .creationDate(accountEntity.getCreationDate())
                .build();

    }

    public Account map(AccountRequest accountRequest) {
        return Account.builder().availableBalance(accountRequest.getBalance())
                .customerId(accountRequest.getCustomerId()).build();
    }

    public AccountResponse mapEntity(Account account) {
        return AccountResponse
                .builder()
                .accountNumber(account.getAccountNumber())
                .availableBalance(account.getAvailableBalance())
                .creationDate(account.getCreationDate())
                .status(account.getStatus())
                .build();
    }


}
