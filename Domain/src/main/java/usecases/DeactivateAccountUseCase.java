package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    //TODO should have deactivation logic here instead of in the repository

    private AccountRepository accountRepository;

    public void execute(Account account) {
        account.setStatus(Status.Inactive);
        accountRepository.save(account);
    }


}
