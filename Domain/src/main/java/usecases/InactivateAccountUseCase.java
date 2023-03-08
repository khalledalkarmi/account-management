package usecases;

import event.Event;
import event.EventPublisher;
import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

@AllArgsConstructor
public class InactivateAccountUseCase {

    private AccountRepository accountRepository;
    private final EventPublisher eventPublisher;

    public void execute(Account account) {
        account.setStatus(Status.Active);
        accountRepository.save(account);

        publishEvent(account);
    }

    private void publishEvent(Object payload) {
        eventPublisher.publish(new Event(payload , "Account Inactivated"));
    }
}
