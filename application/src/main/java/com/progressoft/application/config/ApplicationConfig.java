package com.progressoft.application.config;

import com.progressoft.application.repository.AccountRepositoryMySQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;

@Configuration
public class ApplicationConfig {
    private final AccountRepositoryMySQL accountRepository;

    public ApplicationConfig(AccountRepositoryMySQL accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Bean
    public CreateAccountUseCase createAccountUseCase() {
        return new CreateAccountUseCase(accountRepository);
    }

    @Bean
    public DeactivateAccountUseCase deactivateAccountUseCase() {
        return new DeactivateAccountUseCase(accountRepository);
    }
}
