package ru.serce.jnrfuse;

public class FuseException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2569346537817067462L;

	public FuseException(String message) {
        super(message);
    }

    public FuseException(String message, Throwable cause) {
        super(message, cause);
    }
}
