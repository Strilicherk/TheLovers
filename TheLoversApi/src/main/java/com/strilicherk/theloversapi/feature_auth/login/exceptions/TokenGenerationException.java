package com.strilicherk.theloversapi.feature_auth.login.exceptions;

import com.strilicherk.theloversapi.core.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenGenerationException extends BusinessException {
    public TokenGenerationException(String message, HttpStatus status, String errorCode) {
        super(message, status, errorCode);
    }
}
