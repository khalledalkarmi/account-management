package usecases;

import lombok.AllArgsConstructor;
import model.Account;
import model.Status;
import repository.AccountRepository;

import java.util.Objects;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    private AccountRepository accountRepository;

    public Status execute(Account account) {
        if (Objects.isNull(account))
            throw new NullPointerException("Invalid Account, Account is null");
        Status status = account.getStatus();
        if (Objects.isNull(status))
            throw new NullPointerException("Invalid Account, status is null");
        if (status.equals(Status.Active))
            return accountRepository.deActive(account);

        return account.getStatus();

    }


}
