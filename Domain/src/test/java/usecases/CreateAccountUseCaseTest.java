package usecases;

import model.Account;
import model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    /*
    @Test
    void givenInvalidAccount_whenExecute_thenExceptionIsThrown() {

        Account account = Account.builder()
                .accountNumber(123456L)
                .build();

        Account finalAccount = account;
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> createAccountUseCase.execute(finalAccount))
                .withMessage("Invalid Account id, account id is null");

        account = Account.builder().id("KHALEDKA").build();
        Account finalAccount1 = account;
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> createAccountUseCase.execute(finalAccount1))
                .withMessage("Customer not found");
    }

     */


    @Test
    void givenValidAccount_whenExecute_thenExpectedResult() {
        Account account = Account.builder()
                .customerId("KHALEDKAR")
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .build();

        createAccountUseCase.execute(account);
        verify(accountRepository).save(account);
    }
}