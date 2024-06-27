package com.db.crud.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(CreateAddressException.class)
    public ResponseEntity<String> handleErrorCreateAddressException(CreateAddressException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CreatePersonException.class)
    public ResponseEntity<String> handleErrorCreatePersonException(CreatePersonException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UpdateAddressException.class)
    public ResponseEntity<String> handleErrorUpdateAddressException(UpdateAddressException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UpdatePersonException.class)
    public ResponseEntity<String> handleErrorUpdatePersonException(UpdatePersonException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DeleteAddressException.class)
    public ResponseEntity<String> handleErrorDeleteAddressException(DeleteAddressException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    @ExceptionHandler(DeletePersonException.class)
    public ResponseEntity<String> handleErrorDeletePersonException(DeletePersonException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    
    
    @ExceptionHandler(GetInfoException.class)
    public ResponseEntity<String> handleErrorGetInfoException(GetInfoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ValidateTokenException.class)
    public ResponseEntity<String> handleErrorValidateTokenException(ValidateTokenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
