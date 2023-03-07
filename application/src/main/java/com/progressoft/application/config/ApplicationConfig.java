package com.progressoft.application.config;

import com.progressoft.application.repository.AccountRepository;
import com.progressoft.application.repository.AccountRepositoryMySQL;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usecases.CreateAccountUseCase;

@Configuration
public class ApplicationConfig {
    private final AccountRepositoryMySQL accountRepository;

    public ApplicationConfig(AccountRepositoryMySQL accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Bean
    public CreateAccountUseCase makeBean(){
        return new CreateAccountUseCase(accountRepository);
    }
}
