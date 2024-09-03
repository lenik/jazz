package net.bodz.lily.security.auth;

public class ExtServiceException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public ExtServiceException() {
        super();
    }

    public ExtServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExtServiceException(String message) {
        super(message);
    }

    public ExtServiceException(Throwable cause) {
        super(cause);
    }

}
