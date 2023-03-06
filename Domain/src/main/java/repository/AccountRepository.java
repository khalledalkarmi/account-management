package repository;

import model.Account;
import model.Status;

import java.util.List;

public interface AccountRepository {
    void save(Account account);

    Status deActive(Account account);

    Status inActive(Account account);

    List<Account> findAll();
}
