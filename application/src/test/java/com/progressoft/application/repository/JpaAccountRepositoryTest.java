package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountEntity;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@Transactional
class JpaAccountRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    JpaAccountRepository accountRepository;


    RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    @Test
    public void should_find_no_Account_if_repository_is_empty() {
        List<AccountEntity> tutorials = accountRepository.findAll();
        Assertions.assertThat(tutorials).isEmpty();
    }

    @Test
    public void givenValidAccountEntity_whenSave_thenExpectedResult() {
        AccountEntity accountEntity = getAccount();

        AccountEntity save = accountRepository.save(accountEntity);
        Assertions.assertThat(save).hasFieldOrPropertyWithValue("id", 1L);
        Assertions.assertThat(save).hasFieldOrPropertyWithValue("Status", Status.Active);
    }

    @Test
    public void whenFindAllAccount_thenExpectedResult() {

        AccountEntity account1 = getAccount();
        AccountEntity account2 = getAccount();
        AccountEntity account3 = getAccount();

        testEntityManager.persist(account1);
        testEntityManager.persist(account2);
        testEntityManager.persist(account3);

        List<AccountEntity> all = accountRepository.findAll();
        Assertions.assertThat(all).hasSize(3);
    }

    @Test
    public void whenFindByAccountNumber_thenExpectedResult() {
        AccountEntity account1 = getAccount();
        AccountEntity account2 = getAccount();
        AccountEntity account3 = getAccount();

        testEntityManager.persist(account1);
        testEntityManager.persist(account2);
        testEntityManager.persist(account3);
        long accountNumber = account1.getAccountNumber();
        Optional<AccountEntity> byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        Assertions.assertThat(byAccountNumber.isPresent()).isTrue();
        Assertions.assertThat(byAccountNumber.get()).hasFieldOrPropertyWithValue("accountNumber", accountNumber);
    }

    private AccountEntity getAccount() {
        long randomWithRandomDataGenerator = randomDataGenerator.nextLong(1_000_000_000_000_000L, 9_999_999_999_999_999L);
        return AccountEntity.builder().
                accountNumber(randomWithRandomDataGenerator)
                .creationDate(LocalDateTime.now())
                .availableBalance(BigDecimal.TEN)
                .status(Status.Active)
                .customerId("KHALEDAKR").build();
    }
}