package com.grumvalski.yogaLifebackend.exception;

import com.grumvalski.yogaLifebackend.bean.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, ResourceAccessException.class})
    public ResponseEntity<BaseError> handleGenericException(Exception ex) {

        return new ResponseEntity<>(new BaseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
