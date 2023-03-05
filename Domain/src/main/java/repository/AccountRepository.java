package repository;

import model.Account;

public interface AccountRepository {
    void save(Account account);

    void deActive(Account account);

    void inActive(Account account);
}
