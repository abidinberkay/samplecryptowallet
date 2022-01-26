package com.example.samplecryptowallet.Service;

import com.example.samplecryptowallet.model.Entity.Account;
import com.example.samplecryptowallet.model.Entity.User;
import com.example.samplecryptowallet.model.enums.Currency;
import com.example.samplecryptowallet.repository.AccountRepository;
import com.example.samplecryptowallet.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    private final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(1000);

    @EventListener(ContextRefreshedEvent.class)
    public void initializeFirstUser() {
        if(userRepository.findAll().isEmpty()) {
            User user = new User();
            user.setId(1L);
            user.setUsername("Berkay");
            userRepository.save(user);
            initializeUserAccount(user);
        }
    }

    public User create(User user) {
        User newUser = userRepository.save(user);
        initializeUserAccount(newUser);
        return user;
    }

    private void initializeUserAccount(User user) {
        Account account = new Account();
        account.setUserId(user.getId());
        account.setAccountCurrencyType(String.valueOf(Currency.USDT));
        account.setAmount(INITIAL_BALANCE);

        accountRepository.save(account);
    }
}
