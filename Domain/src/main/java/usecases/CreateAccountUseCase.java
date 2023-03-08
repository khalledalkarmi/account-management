package usecases;

import event.Event;
import event.EventPublisher;
import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import repository.AccountRepository;
import validator.CreateAccountValidator;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final CreateAccountValidator createAccountValidator;
    private final EventPublisher eventPublisher;

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
        publishEvent(account , "ACCOUNT CREATED");
    }

    private void publishEvent(Object payload , String message) {
        eventPublisher.publish(new Event(payload , message));
    }
}
