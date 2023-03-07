package repository;

import model.Account;
import model.Status;

import java.util.List;

public interface AccountRepository {
    void save(Account account);

    Status deActive(String id);

    Status inActive(String id);

    List<Account> findAll();

    Account findByID(String id);
}
