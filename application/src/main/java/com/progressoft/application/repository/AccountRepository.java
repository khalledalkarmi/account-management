package com.progressoft.application.repository;

import model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account , String> {

}
