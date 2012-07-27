package net.bodz.bas.loader;

public class LoadException
        extends Exception {

    private static final long serialVersionUID = 5449221151871597792L;

    public LoadException() {
        super();
    }

    public LoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadException(String message) {
        super(message);
    }

    public LoadException(Throwable cause) {
        super(cause);
    }

}
