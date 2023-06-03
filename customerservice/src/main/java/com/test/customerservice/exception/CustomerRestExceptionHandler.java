package com.test.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> CustomerNotFound(CustomerNotFoundException e) {

        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setTimeStamp(System.currentTimeMillis());
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> InputMissMatch(MethodArgumentTypeMismatchException e) {

        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setTimeStamp(System.currentTimeMillis());
        error.setMessage("Value must be number");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> globalException(Exception e) {

        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setTimeStamp(System.currentTimeMillis());
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
