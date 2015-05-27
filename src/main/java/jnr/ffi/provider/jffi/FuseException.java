package jnr.ffi.provider.jffi;

public class FuseException extends RuntimeException {
    public FuseException(String message) {
        super(message);
    }

    public FuseException(String message, Throwable cause) {
        super(message, cause);
    }
}
