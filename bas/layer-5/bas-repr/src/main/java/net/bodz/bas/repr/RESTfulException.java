package net.bodz.bas.repr;

public class RESTfulException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public RESTfulException() {
        super();
    }

    public RESTfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public RESTfulException(String message) {
        super(message);
    }

    public RESTfulException(Throwable cause) {
        super(cause);
    }

}
