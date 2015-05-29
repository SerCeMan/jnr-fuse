package ru.serce.jnrfuse;

public class FuseException extends RuntimeException {
    public FuseException(String message) {
        super(message);
    }

    public FuseException(String message, Throwable cause) {
        super(message, cause);
    }
}
