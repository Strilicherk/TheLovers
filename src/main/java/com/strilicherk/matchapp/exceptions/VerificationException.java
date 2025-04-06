package com.strilicherk.matchapp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VerificationException extends RuntimeException {
    private final HttpStatus statusCode;

    public VerificationException(HttpStatus statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
