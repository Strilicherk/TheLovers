package com.strilicherk.matchapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class VerificationException extends RuntimeException {
    private final Integer statusCode;

    public VerificationException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
