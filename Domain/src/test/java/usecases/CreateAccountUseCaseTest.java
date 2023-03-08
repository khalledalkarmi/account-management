package usecases;

import model.Account;
import model.Customer;
import model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;
import validator.CreateAccountValidator;
import validator.CustomerProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerProvider customerProvider;
    @Mock
    private CreateAccountValidator createAccountValidator;
    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @Test
    void givenValidAccount_whenExecute_thenExpectedResult() {
        Account account = Account.builder()
                .customerId("KHALEDKAR")
                .availableBalance(BigDecimal.valueOf(3025.5015))
                .build();
        when(createAccountValidator.validate(account)).thenReturn(true);
//        when(customerProvider.getAllCustomer()).thenReturn(List.of(new Customer("KHALEDKAR")));
        createAccountUseCase.execute(account);
        verify(accountRepository).save(account);
    }
}