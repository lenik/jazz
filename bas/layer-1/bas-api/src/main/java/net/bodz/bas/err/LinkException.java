package net.bodz.bas.err;

public class LinkException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public LinkException() {
        super();
    }

    public LinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkException(String message) {
        super(message);
    }

    public LinkException(Throwable cause) {
        super(cause);
    }

}
