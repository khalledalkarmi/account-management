package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import repository.AccountRepository;
import validator.CreateAccountValidator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateAccountUseCase {

    //TODO should have a dependency on CustomersProvider

    //TODO should set account data here, number, status, etc...

    private final AccountRepository accountRepository;

    public void execute(Account account) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        long randomWithRandomDataGenerator = randomDataGenerator.nextLong(1_000_000_000_000_000L, 9_999_999_999_999_999L);
        account.setStatus(Status.Active);
        account.setAccountNumber(randomWithRandomDataGenerator);
        account.setCreationDate(LocalDateTime.now());
        if (!CreateAccountValidator.validate(account))
            throw new IllegalArgumentException("Customer not found");

        System.out.println(account.getAccountNumber());
        accountRepository.save(account);
    }
}
