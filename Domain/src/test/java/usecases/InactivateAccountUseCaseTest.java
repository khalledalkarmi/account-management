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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InactivateAccountUseCaseTest {


    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    InactivateAccountUseCase inactivateAccountUseCase;

    @Test
    void givenInvalidAccount_whenExecute_thenExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                inactivateAccountUseCase.execute(null)).withMessage("Invalid Account, Account is null");
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                inactivateAccountUseCase.execute(new Account())).withMessage("Invalid Account, status is null");
    }

    /*
    @Test
    void givenValidAccount_whenExecute_thenExpectedResult() {
        Account account = Account.builder()
                .id("KHALEDKAR")
                .creationDate(LocalDateTime.now())
                .status(Status.Inactive)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();

        when(accountRepository.inActive(anyString())).thenReturn(Status.Active);
        System.out.println(inactivateAccountUseCase.execute(account));
        verify(accountRepository).inActive(account.getId());
    }

     */
}