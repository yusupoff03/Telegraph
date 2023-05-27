package com.example.telegraph.config;

import com.example.telegraph.exception.AuthenticationFailedException;
import com.example.telegraph.exception.ConflictException;
import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.exception.RequestValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AuthenticationFailedException.class})
    public ResponseEntity<String> AuthenticationFailedException(
            AuthenticationFailedException e
    ) {
      return ResponseEntity.status(401).body(e.getMessage());
    }
    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<String>ConflictExceptionHandler(
            ConflictException e
    ){
        return ResponseEntity.status(409).body(e.getMessage());
    }
    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<String>DataNotFoundExceptionHandler(
            DataNotFoundException e
    ){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(value = {RequestValidationException.class})
    public ResponseEntity<String>RequestValidationExceptionHandler(
            RequestValidationException e
    ){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
