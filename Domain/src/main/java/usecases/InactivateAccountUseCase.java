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

        publishEvent(account , "Account Deactivated");
    }

    private void publishEvent(Object payload , String message) {
        eventPublisher.publish(new Event(payload , message));
    }
}
