package com.example.samplecryptowallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCurrencyException extends RuntimeException{

    public InvalidCurrencyException(String errorMessage) {
        super(errorMessage);
    }

}
