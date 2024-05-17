package com.db.crud.person.exception;

public class DuplicateMainAddressException extends RuntimeException {
    public DuplicateMainAddressException(String message) {
        super(message);
    }
}
