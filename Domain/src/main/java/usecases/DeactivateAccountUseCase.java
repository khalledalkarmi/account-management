package usecases;

import event.eventusecases.ChangeStatusEventUseCase;
import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    //DONE should have deactivation logic here instead of in the repository
    private AccountRepository accountRepository;
    private final ChangeStatusEventUseCase changeStatusEventUseCase;

    public void execute(Account account) {
        account.setStatus(Status.Inactive);
        accountRepository.save(account);
        changeStatusEventUseCase.execute();
    }


}
