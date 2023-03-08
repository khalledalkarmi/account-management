package usecases;

import event.Event;
import event.EventPublisher;
import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    //DONE should have deactivation logic here instead of in the repository
    private AccountRepository accountRepository;
    private EventPublisher eventPublisher;

    public void execute(Account account) {
        account.setStatus(Status.Inactive);
        accountRepository.save(account);

        publishEvent(account , "Account Deactivated");
    }

    private void publishEvent(Object payload , String message) {
        eventPublisher.publish(new Event(payload , message));
    }


}
