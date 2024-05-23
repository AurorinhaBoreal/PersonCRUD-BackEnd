package com.db.crud.person.exception;

public class DuplicateCpfException extends RuntimeException {
    public DuplicateCpfException(String message) {
        super(message);
    }
}
