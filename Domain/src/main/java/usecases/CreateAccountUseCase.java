package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import repository.AccountRepository;
import validator.CreateAccountValidator;

@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;


    public void execute(Account account) {
        if (!CreateAccountValidator.validate(account))
            throw new IllegalArgumentException("Not Found The Customer");
        accountRepository.save(account);

    }
}
