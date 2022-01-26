package com.example.samplecryptowallet.Service;

import com.example.samplecryptowallet.exception.NoAvailableAccountException;
import com.example.samplecryptowallet.model.Entity.Account;
import com.example.samplecryptowallet.model.enums.Currency;
import com.example.samplecryptowallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    private final String ACCOUNT_ERROR_MSG = "User has no available dollar account";

    public List<Account> getUserAccounts(Long userId) {
        return accountRepository.getAccountsByUserId(userId);
    }

    public Account getUserUsdtAccountDetail(Long userId) {
        return accountRepository.getAccountByUserIdAndAccountCurrencyType(userId, Currency.USDT.name())
                .orElseThrow(() -> new NoAvailableAccountException(ACCOUNT_ERROR_MSG));
    }

}
