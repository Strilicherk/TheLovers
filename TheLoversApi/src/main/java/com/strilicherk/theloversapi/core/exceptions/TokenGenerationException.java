package com.strilicherk.theloversapi.core.exceptions;

import org.springframework.http.HttpStatus;

public class TokenGenerationException extends BusinessException {
    public TokenGenerationException(String message, HttpStatus status, String errorCode) {
        super(message, status, errorCode);
    }
}
