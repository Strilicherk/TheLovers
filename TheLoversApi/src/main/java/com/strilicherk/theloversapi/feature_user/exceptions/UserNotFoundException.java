package com.strilicherk.theloversapi.feature_user.exceptions;

import com.strilicherk.theloversapi.core.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "USER_NOT_FOUND");
    }
}
