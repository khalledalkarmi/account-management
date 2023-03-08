package repository;

import model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void save(Account account);

    List<Account> findAll();

    Optional<Account> findByAccountNumber(String id);
}
