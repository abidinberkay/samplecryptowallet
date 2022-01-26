package com.example.samplecryptowallet.exception;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ErrorDetails setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDetails setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetails setDetails(String details) {
        this.details = details;
        return this;
    }
}
