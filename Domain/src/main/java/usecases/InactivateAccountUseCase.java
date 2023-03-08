package usecases;

import event.eventusecases.ChangeStatusEventUseCase;
import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

@AllArgsConstructor
public class InactivateAccountUseCase {

    private AccountRepository accountRepository;
    private final ChangeStatusEventUseCase changeStatusEventUseCase;

    public void execute(Account account) {
        account.setStatus(Status.Active);
        accountRepository.save(account);
        changeStatusEventUseCase.execute();
    }
}
