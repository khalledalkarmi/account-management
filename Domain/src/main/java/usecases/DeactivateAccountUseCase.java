package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import repository.AccountRepository;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    private AccountRepository accountRepository;

    void execute(Account account) {
        accountRepository.deActive(account);
    }
}
