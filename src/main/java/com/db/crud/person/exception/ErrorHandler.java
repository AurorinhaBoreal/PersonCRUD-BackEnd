package com.db.crud.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleErrorNoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Endpoint was not found: "+e.getResourcePath());
    }
 
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handleErrorPersonNotFoundException(PersonNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleErrorAddressNotFoundException(AddressNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DuplicateCpfException.class)
    public ResponseEntity<String> handleErrorDuplicateCpfException(DuplicateCpfException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleErrorConstraintViolation(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String answer = result.getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest()
                .body(answer);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleErrorHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A value was not informed in the correct formatation");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleErrorNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An Required field was not filled.");
    }

    @ExceptionHandler(DuplicateMainAddressException.class)
    public ResponseEntity<String> handleErrrorDuplicateMainAddressException(DuplicateMainAddressException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
