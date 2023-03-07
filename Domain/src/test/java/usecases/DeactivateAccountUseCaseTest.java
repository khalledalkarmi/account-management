package usecases;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class DeactivateAccountUseCaseTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private DeactivateAccountUseCase deactivateAccountUseCase;

    /*
    @Test
    void givenInvalidAccount_whenExecute_thenExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                deactivateAccountUseCase.execute(null)).withMessage("Invalid Account, Account is null");
        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                deactivateAccountUseCase.execute(new Account())).withMessage("Invalid Account, status is null");
    }

    @Test
    void givenValidAccount_whenExecute_thenExpectedResult() {
        Account account = Account.builder()
                .id("KHALEDKAR")
                .creationDate(LocalDateTime.now())
                .status(Status.Active)
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .accountNumber(123456L)
                .build();

        when(accountRepository.deActive(anyString())).thenReturn(Status.Inactive);
        deactivateAccountUseCase.execute(account.getId());
        verify(accountRepository).deActive("");
    }

     */
}