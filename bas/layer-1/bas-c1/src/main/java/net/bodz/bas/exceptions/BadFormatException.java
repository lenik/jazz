package net.bodz.bas.exceptions;

public class BadFormatException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public BadFormatException() {
        super();
    }

    public BadFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormatException(String message) {
        super(message);
    }

    public BadFormatException(Throwable cause) {
        super(cause);
    }

}
