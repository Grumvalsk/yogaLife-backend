package com.grumvalski.yogaLifebackend.exception;

import com.grumvalski.yogaLifebackend.bean.BaseError;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class, ResourceAccessException.class})
    public ResponseEntity<BaseError> handleGenericException(Exception ex) {
        LOGGER.warn("ERROR: {} ", ex.getMessage());
        return new ResponseEntity<>(new BaseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Utente non autorizzato"), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
