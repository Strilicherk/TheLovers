package com.strilicherk.theloversapi.core.handlers;

import com.strilicherk.theloversapi.core.exceptions.BusinessException;
import com.strilicherk.theloversapi.core.domain.model.shared.ResponseDTO;
import com.strilicherk.theloversapi.core.exceptions.ErrorResponse;
import com.strilicherk.theloversapi.core.exceptions.GenericException;
import com.strilicherk.theloversapi.core.exceptions.VerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDTO<Object>> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ResponseDTO<>(ex.getStatus().value(), ex.getMessage(), false, null));
    }

    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ErrorResponse> handleVerificationException(VerificationException ex) {
        ErrorResponse error = new ErrorResponse(ex.getStatusCode().value(), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
