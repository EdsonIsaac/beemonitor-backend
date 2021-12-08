package io.github.edsonisaac.beemonitor.infraestrutura.exception;

public class OperationFailedException extends RuntimeException {

    public OperationFailedException() {
        super();
    }

    public OperationFailedException(String message) {
        super(message);
    }

    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}