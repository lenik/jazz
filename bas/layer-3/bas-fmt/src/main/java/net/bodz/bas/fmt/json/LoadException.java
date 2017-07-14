package net.bodz.bas.fmt.json;

public class LoadException
        extends Exception {

    private static final long serialVersionUID = 1L;

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
