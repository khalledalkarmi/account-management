package com.progressoft.application.config;

import com.progressoft.application.repository.JpaAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.AccountRepository;
import usecases.CreateAccountUseCase;
import usecases.DeactivateAccountUseCase;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository) {
        return new CreateAccountUseCase(accountRepository);
    }

    @Bean
    public DeactivateAccountUseCase deactivateAccountUseCase(AccountRepository accountRepository) {
        return new DeactivateAccountUseCase(accountRepository);
    }
}
