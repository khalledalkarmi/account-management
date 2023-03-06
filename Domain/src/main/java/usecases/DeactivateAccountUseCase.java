package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import repository.AccountRepository;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    private AccountRepository accountRepository;

    public void execute(Account account) {
        accountRepository.deActive(account);
    }


}
