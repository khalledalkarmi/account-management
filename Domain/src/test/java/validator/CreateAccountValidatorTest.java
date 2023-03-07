package validator;

import model.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class CreateAccountValidatorTest {

    @Test
    void givenInvalidAccount_whenValidate_thenExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> CreateAccountValidator.validate(null))
                .withMessage("Invalid Account, account is null");

        Assertions.assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> CreateAccountValidator.validate(new Account()));

    }

    @Test
    public void givenValidAccount_whenValidate_thenExpectedResult() {



    }
}