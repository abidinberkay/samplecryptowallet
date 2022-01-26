package com.example.samplecryptowallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String errorMessage) {
        super(errorMessage);
    }

}
