package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import repository.AccountRepository;

@AllArgsConstructor
public class InactivateAccountUseCase {

    private AccountRepository accountRepository;

    public void execute(Account account) {
        accountRepository.inActive(account);
    }
}
