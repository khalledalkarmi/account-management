package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import repository.AccountRepository;
import validator.CreateAccountValidator;

@AllArgsConstructor
public class CreateAccountUseCase {

    //TODO should have a dependency on CustomersProvider

    //TODO should set account data here, number, status, etc...

    private final AccountRepository accountRepository;

    public void execute(Account account) {
        if (!CreateAccountValidator.validate(account))
            throw new IllegalArgumentException("Customer not found");

        accountRepository.save(account);
    }
}
