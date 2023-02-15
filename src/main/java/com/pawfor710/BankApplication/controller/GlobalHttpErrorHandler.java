package com.pawfor710.BankApplication.controller;

import com.pawfor710.BankApplication.exception.NoMoneyException;
import com.pawfor710.BankApplication.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleNoMoneyException(NoMoneyException e) {
        return new ResponseEntity<>("You do not have that much money to make transaction", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUserNotFoundException (UserNotFoundException e) {
        return new ResponseEntity<>("User with this id does not exist", HttpStatus.BAD_REQUEST);
    }
}
