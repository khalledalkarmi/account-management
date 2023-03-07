package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

@AllArgsConstructor
public class InactivateAccountUseCase {

    private AccountRepository accountRepository;

    public void execute(Account account) {
        account.setStatus(Status.Active);
        accountRepository.save(account);

    }
}
