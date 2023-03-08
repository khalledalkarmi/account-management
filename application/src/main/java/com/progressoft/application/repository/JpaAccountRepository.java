package com.progressoft.application.repository;

import com.progressoft.application.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByAccountNumber(long accountNumber);

}
