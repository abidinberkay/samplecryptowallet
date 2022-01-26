package com.example.samplecryptowallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidTradeRequestException extends RuntimeException {

    public InvalidTradeRequestException(String errorMessage) {
        super(errorMessage);
    }

}
