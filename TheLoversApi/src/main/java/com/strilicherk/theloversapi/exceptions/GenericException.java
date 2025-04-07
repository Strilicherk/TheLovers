package com.strilicherk.theloversapi.exceptions;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    public GenericException(String message) {
        super(message);
    }
}
