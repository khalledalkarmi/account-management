package com.progressoft.application.config;

import com.progressoft.application.entity.AccountMapper;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import com.progressoft.application.repository.JpaAccountRepository;
import event.EventPublisher;
import event.eventusecases.ChangeStatusEventUseCase;
import event.eventusecases.CreateAccountEventUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.AccountRepository;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;
import usecases.InactivateAccountUseCase;
import validator.CustomerProvider;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, CustomerProvider customerProvider, CreateAccountEventUseCase createAccountEventUseCase) {
        return new CreateAccountUseCase(accountRepository, customerProvider, createAccountEventUseCase);
    }

    @Bean
    public DeactivateAccountUseCase deactivateAccountUseCase(AccountRepository accountRepository, ChangeStatusEventUseCase changeStatusEventUseCase) {
        return new DeactivateAccountUseCase(accountRepository, changeStatusEventUseCase);
    }

    @Bean
    public InactivateAccountUseCase inactivateAccountUseCase(AccountRepository accountRepository, ChangeStatusEventUseCase changeStatusEventUseCase) {
        return new InactivateAccountUseCase(accountRepository, changeStatusEventUseCase);
    }

    @Bean
    public CreateAccountEventUseCase createAccountEventUseCase(EventPublisher eventPublisher) {
        return new CreateAccountEventUseCase(eventPublisher);
    }

    @Bean
    public ChangeStatusEventUseCase changeStatusEventUseCase(EventPublisher eventPublisher) {
        return new ChangeStatusEventUseCase(eventPublisher);
    }

}
