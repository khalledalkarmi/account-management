package usecases;

import lombok.AllArgsConstructor;
import repository.AccountRepository;

@AllArgsConstructor
public class DeactivateAccountUseCase {

    //TODO should have deactivation logic here instead of in the repository

    private AccountRepository accountRepository;

    public void execute(String id) {
        accountRepository.deActive(id);
    }


}
