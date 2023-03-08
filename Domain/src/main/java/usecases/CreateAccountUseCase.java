package usecases;

import event.eventusecases.CreateAccountEventUseCase;
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
    private final CreateAccountEventUseCase createAccountEventUseCase;

    public CreateAccountUseCase(AccountRepository accountRepository, CustomerProvider customerProvider, CreateAccountEventUseCase createAccountEventUseCase){
        this.accountRepository = accountRepository;
        this.createAccountValidator = new CreateAccountValidator(customerProvider);
        this.createAccountEventUseCase = createAccountEventUseCase;
    }

    public void execute(Account account) {
        if (!createAccountValidator.validate(account))
            throw new IllegalArgumentException("Customer not found");

        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        long randomWithRandomDataGenerator = randomDataGenerator.nextLong(1_000_000_000_000_000L, 9_999_999_999_999_999L);
        account.setStatus(Status.Active);
        account.setAccountNumber(randomWithRandomDataGenerator);
        account.setCreationDate(LocalDateTime.now());

        accountRepository.save(account);

        //TODO This use case doesn't have to be a separate use case, just a private method
        createAccountEventUseCase.execute();
    }
}
