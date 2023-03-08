package usecases;

import event.Event;
import event.EventPublisher;
import model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;
import validator.CreateAccountValidator;
import validator.CustomerProvider;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerProvider customerProvider;
    @Mock
    private CreateAccountValidator createAccountValidator;
    @Mock
    private EventPublisher eventPublisher;
    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @Test
    void givenValidAccount_whenExecute_thenExpectedResult() {
        Account account = Account.builder()
                .customerId("KHALEDKAR")
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .build();
        when(createAccountValidator.validate(account)).thenReturn(Collections.emptyList());
        doNothing().when(eventPublisher).publish(any(Event.class));
        createAccountUseCase.execute(account);
        verify(accountRepository).save(account);
    }
}