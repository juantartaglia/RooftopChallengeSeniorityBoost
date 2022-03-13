package com.rooftop.challenge.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ApiError> handleCustomException(CustomException customException) {
        ApiError apiError = new ApiError(customException.getError() , customException.getMessage(), customException.getCode());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.valueOf(apiError.getCode()));
    }
}