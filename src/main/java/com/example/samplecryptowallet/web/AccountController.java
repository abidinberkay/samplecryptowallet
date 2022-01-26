package com.example.samplecryptowallet.web;

import com.example.samplecryptowallet.Service.AccountService;
import com.example.samplecryptowallet.exception.UserNotFoundException;
import com.example.samplecryptowallet.model.Entity.Account;
import com.example.samplecryptowallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baraka/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserRepository userRepository;

    private final String USER_ERROR_MSG = "User not found";

    @GetMapping("/list")
    public ResponseEntity<List<Account>> list(@RequestParam(name = "userId") Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_ERROR_MSG));
        return ResponseEntity.ok(accountService.getUserAccounts(userId));
    }

    @GetMapping("/remainingUsdtBalance")
    public ResponseEntity<Account> remainingUsdtBalance(@RequestParam(name = "userId") Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_ERROR_MSG));
        return ResponseEntity.ok(accountService.getUserUsdtAccountDetail(userId));
    }


}
