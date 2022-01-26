package com.example.samplecryptowallet.repository;

import com.example.samplecryptowallet.model.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> getAccountByUserIdAndAccountCurrencyType(Long userId, String accountCurrencyType);

    List<Account> getAccountsByUserId(Long userId);

}
