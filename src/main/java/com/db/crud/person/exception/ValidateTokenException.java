package com.db.crud.person.exception;


// Cada construtor é utilizado para a validação de determinada exceção
public class ValidateTokenException extends RuntimeException {
    public ValidateTokenException(String message, Throwable e) {
        super(message, e);
    }

    public ValidateTokenException(String message) {
        super(message);
    }
    public ValidateTokenException() {
        super();
    }
}
