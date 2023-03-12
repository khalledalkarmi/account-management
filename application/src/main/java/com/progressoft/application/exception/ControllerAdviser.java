package com.progressoft.application.exception;

import exception.CustomerNotFoundException;
import exception.InvalidAccountException;
import exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {

        ErrorResponse response = new ErrorResponse();
        response.setMessage("User not found");
        response.setStatusCode(HttpStatus.NOT_FOUND);
        response.setUrl(((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(
            CustomerNotFoundException exception
            , WebRequest request) {

        ErrorResponse response = new ErrorResponse();
        response.setStatusCode(HttpStatus.NOT_FOUND);
        response.setUrl(((ServletWebRequest) request).getRequest().getRequestURI());
        response.setMessage("Customer not found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<Object> handleInvalidAccountException(
            InvalidAccountException exception
            , WebRequest request) {

        ErrorResponse response = new ErrorResponse();
        response.setStatusCode(HttpStatus.NOT_FOUND);
        response.setUrl(((ServletWebRequest) request).getRequest().getRequestURI());
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
