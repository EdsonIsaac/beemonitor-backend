package io.github.edsonisaac.beemonitor.infraestrutura.exception;

public class UnauthorizedAcessException extends RuntimeException {

    public UnauthorizedAcessException() {
        super();
    }

    public UnauthorizedAcessException(String message) {
        super(message);
    }

    public UnauthorizedAcessException(String message, Throwable cause) {
        super(message, cause);
    }
}