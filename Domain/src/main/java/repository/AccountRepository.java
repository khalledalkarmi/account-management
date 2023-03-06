package repository;

import model.Account;

import java.util.List;

public interface AccountRepository {
    void save(Account account);

    void deActive(Account account);

    void inActive(Account account);

    List<Account> findAll();
}
