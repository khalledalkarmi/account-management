package validator;

import model.Account;
import model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateAccountValidatorTest {

    @Mock
    private CustomerProvider customerProvider;

    @InjectMocks
    private CreateAccountValidator createAccountValidator;

    @Test
    void givenInvalidAccount_whenValidate_thenExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> createAccountValidator.validate(null))
                .withMessage("Invalid Account, account is null");

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> createAccountValidator.validate(new Account()));

    }

    @Test
    public void givenValidAccount_whenValidate_thenExpectedResult() {

    }
}