package com.progressoft.application.events;

import com.progressoft.application.repository.AccountRepositoryMySQL;
import model.Account;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.event.EventListener;
import usecases.DeactivateAccountUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
public class ChangeStatusEventTest {
    @SpyBean
    private EventTestListener eventListener;
    @MockBean
    AccountRepositoryMySQL accountRepository;

    @SpyBean
    DeactivateAccountUseCase deactivateAccountUseCase;

    @Test
    public void testEventFires() {
        Account account = getAccount();
        doNothing().when(accountRepository).save(account);
        deactivateAccountUseCase.execute(account);
    }

    @TestComponent
    public static class EventTestListener {
        @EventListener
        public void handle(SpringEvent event) {
            Assertions.assertThat(event.getMessage()).isEqualTo("ACCOUNT_STATUS_CHANGED");
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

