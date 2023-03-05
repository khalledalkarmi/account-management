package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import repository.AccountRepository;

@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    void execute(Account account) {
        accountRepository.save(account);
    }
}
