package io.github.edsonisaac.monitoramentocomeia.infraestrutura.exception;

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