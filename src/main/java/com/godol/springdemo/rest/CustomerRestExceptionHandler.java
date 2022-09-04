package com.godol.springdemo.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
    // 핸들러 처리하기 404 에러
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handlerException(CustomerNotFoundException exc){
        CustomerErrorResponse response = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 다른 핸들러 처리하기 (모든 예외) 400 에러
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handlerException(Exception exc){
        CustomerErrorResponse response = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
