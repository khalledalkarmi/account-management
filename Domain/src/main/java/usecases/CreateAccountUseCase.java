package usecases;

import model.Account;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import repository.AccountRepository;
import validator.CreateAccountValidator;
import validator.CustomerProvider;

import java.time.LocalDateTime;


public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final CreateAccountValidator createAccountValidator;

    public CreateAccountUseCase(AccountRepository accountRepository, CustomerProvider customerProvider){
        this.accountRepository = accountRepository;
        this.createAccountValidator = new CreateAccountValidator(customerProvider);
    }

    public void execute(Account account) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        long randomWithRandomDataGenerator = randomDataGenerator.nextLong(1_000_000_000_000_000L, 9_999_999_999_999_999L);
        account.setStatus(Status.Active);
        account.setAccountNumber(randomWithRandomDataGenerator);
        account.setCreationDate(LocalDateTime.now());
        if (!createAccountValidator.validate(account))
            throw new IllegalArgumentException("Customer not found");
        accountRepository.save(account);
    }
}
