package com.strilicherk.theloversapi.feature_user.exceptions;

import com.strilicherk.theloversapi.core.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT, "USER_ALREADY_EXISTS");
    }
}
