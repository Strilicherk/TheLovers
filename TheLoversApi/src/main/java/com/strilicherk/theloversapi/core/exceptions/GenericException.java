package com.strilicherk.theloversapi.core.exceptions;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    public GenericException(String message) {
        super(message);
    }
}
