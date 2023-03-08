package usecases;

import event.Event;
import event.EventPublisher;
import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import org.apache.commons.math3.random.RandomDataGenerator;
import repository.AccountRepository;
import validator.CreateAccountValidator;
import validator.Violation;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final CreateAccountValidator createAccountValidator;
    private final EventPublisher eventPublisher;

    public void execute(Account account) {
        List<Violation> validate = createAccountValidator.validate(account);
        if (validate.size() != 0) {
            for (Violation violation : validate) {
                throw violation.getException();
            }
        }

        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        long randomWithRandomDataGenerator = randomDataGenerator.nextLong(1_000_000_000_000_000L, 9_999_999_999_999_999L);
        account.setStatus(Status.Active);
        account.setAccountNumber(randomWithRandomDataGenerator);
        account.setCreationDate(LocalDateTime.now());

        accountRepository.save(account);

        //TODO This use case doesn't have to be a separate use case, just a private method
        publishEvent(account);
    }

    private void publishEvent(Object payload) {
        eventPublisher.publish(new Event(payload, "ACCOUNT CREATED"));
    }
}
