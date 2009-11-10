package net.bodz.bas.dnb;

public class NoSuchConstException extends RuntimeException {

    private static final long serialVersionUID = -3416559667196537369L;

    public NoSuchConstException() {
        super();
    }

    public NoSuchConstException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchConstException(String message) {
        super(message);
    }

    public NoSuchConstException(Throwable cause) {
        super(cause);
    }

}
