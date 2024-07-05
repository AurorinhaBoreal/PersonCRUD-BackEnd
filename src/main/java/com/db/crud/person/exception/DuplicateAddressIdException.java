package com.db.crud.person.exception;

public class DuplicateAddressIdException extends RuntimeException{
    public DuplicateAddressIdException(String message) {
        super(message);
      }
}
