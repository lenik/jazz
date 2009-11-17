package net.bodz.bas.api.exceptions;

public class CancelException extends RuntimeException {

    private static final long serialVersionUID = 2709788898818459284L;

    public CancelException() {
        super();
    }

    public CancelException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancelException(String message) {
        super(message);
    }

    public CancelException(Throwable cause) {
        super(cause);
    }

}
