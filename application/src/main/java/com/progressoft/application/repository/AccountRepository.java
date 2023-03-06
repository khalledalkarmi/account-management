package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountEntity;
import model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

}
