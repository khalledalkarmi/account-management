package com.progressoft.application.events;

import com.progressoft.application.entity.AccountEntity;
import model.Account;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.event.EventListener;
import repository.AccountRepository;
import usecases.CreateAccountUseCase;
import validator.CreateAccountValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateAccountEventTest {
    @SpyBean
    private EventTestListener eventListener;
    @SpyBean
    CreateAccountUseCase createAccountUseCase;
    @SpyBean
    AccountRepository accountRepository;
    @MockBean
    CreateAccountValidator createAccountValidator;

    @Test
    public void testEventFires() {
        Account account = getAccount();
        when(createAccountValidator.validate(account)).thenReturn(true);
        doNothing().when(accountRepository).save(account);
        createAccountUseCase.execute(account);
    }
    @TestComponent
    public static class EventTestListener {
        @EventListener
        public void handle(SpringEvent event) {
            Assertions.assertThat(event.getMessage()).isEqualTo("ACCOUNT_CREATED");
        }
    }

    private Account getAccount() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        long randomWithRandomDataGenerator = randomDataGenerator.nextLong(1_000_000_000_000_000L, 9_999_999_999_999_999L);
        return Account.builder().
                accountNumber(randomWithRandomDataGenerator)
                .creationDate(LocalDateTime.now())
                .availableBalance(BigDecimal.TEN)
                .status(Status.Active)
                .customerId("KHALEDAKR").build();
    }
}

