package com.example.samplecryptowallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoAvailableAccountException.class)
    public ResponseEntity<?> noAvailableAccountException(NoAvailableAccountException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> insufficientBalanceException(InsufficientBalanceException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<?> invalidCurrencyException(InvalidCurrencyException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTradeRequestException.class)
    public ResponseEntity<?> invalidTradeRequestException(InvalidTradeRequestException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
