package com.progressoft.application.config;

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
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository, CustomerProvider customerProvider) {
        return new CreateAccountUseCase(accountRepository, customerProvider);
    }

    @Bean
    public DeactivateAccountUseCase deactivateAccountUseCase(AccountRepository accountRepository) {
        return new DeactivateAccountUseCase(accountRepository);
    }

    @Bean
    public InactivateAccountUseCase inactivateAccountUseCase(AccountRepository accountRepository) {
        return new InactivateAccountUseCase(accountRepository);
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
