package com.strilicherk.theloversapi.feature_user.exceptions;

import com.strilicherk.theloversapi.core.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class UserCreationException extends BusinessException {
    public UserCreationException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, "FAILED_TO_CREATE_USER");
    }
}
