package com.progressoft.application.config;

import event.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.AccountRepository;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;
import usecases.InactivateAccountUseCase;
import validator.CreateAccountValidator;
import validator.CustomerProvider;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, CreateAccountValidator createAccountValidator, EventPublisher eventPublisher) {
        return new CreateAccountUseCase(accountRepository, createAccountValidator, eventPublisher);
    }

    @Bean
    public DeactivateAccountUseCase deactivateAccountUseCase(AccountRepository accountRepository, EventPublisher eventPublisher) {
        return new DeactivateAccountUseCase(accountRepository, eventPublisher);
    }

    @Bean
    public InactivateAccountUseCase inactivateAccountUseCase(AccountRepository accountRepository, EventPublisher eventPublisher) {
        return new InactivateAccountUseCase(accountRepository, eventPublisher);
    }

    @Bean
    public CreateAccountValidator createAccountValidator(CustomerProvider customerProvider) {
        return new CreateAccountValidator(customerProvider);
    }

}
