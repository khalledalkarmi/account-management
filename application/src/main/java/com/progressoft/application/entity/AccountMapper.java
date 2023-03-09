package com.progressoft.application.entity;

import com.progressoft.application.resources.AccountRequest;
import com.progressoft.application.resources.AccountResponse;
import exception.InvalidAccountException;
import model.Account;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountMapper {

    //TODO use mapToX or toX naming convention for mappers methods

    public AccountEntity toAccountEntity(Account account) {
        return new AccountEntity.AccountEntityBuilder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .accountNumber(account.getAccountNumber())
                .status(account.getStatus())
                .creationDate(account.getCreationDate())
                .availableBalance(account.getAvailableBalance())
                .build();
    }

    public Account toAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .customerId(accountEntity.getCustomerId())
                .accountNumber(accountEntity.getAccountNumber())
                .availableBalance(accountEntity.getAvailableBalance())
                .status(accountEntity.getStatus())
                .creationDate(accountEntity.getCreationDate())
                .build();

    }

    public Account toAccount(AccountRequest accountRequest) {
        if (!NumberUtils.isCreatable(accountRequest.getBalance()))
            throw new InvalidAccountException("Invalid Balance , should be number");
        return Account.builder()
                .availableBalance(new BigDecimal(accountRequest.getBalance()))
                .customerId(accountRequest.getCustomerId())
                .build();
    }

    public AccountResponse toAccountResponse(Account account) {
        return AccountResponse
                .builder()
                .accountNumber(account.getAccountNumber())
                .availableBalance(account.getAvailableBalance())
                .creationDate(account.getCreationDate())
                .status(account.getStatus())
                .build();
    }

}
