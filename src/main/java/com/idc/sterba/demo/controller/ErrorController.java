package com.idc.sterba.demo.controller;

import com.idc.sterba.demo.exception.runtime.EmailAlreadyExistsException;
import com.idc.sterba.demo.exception.runtime.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity handleException(UsernameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username already exists");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity handleException(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email already exists");
    }
}
