package net.bodz.bas.err;

public class LoaderException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public LoaderException() {
        super();
    }

    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoaderException(String message) {
        super(message);
    }

    public LoaderException(Throwable cause) {
        super(cause);
    }

}
