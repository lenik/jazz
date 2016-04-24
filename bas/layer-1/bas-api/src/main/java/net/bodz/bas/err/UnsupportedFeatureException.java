package net.bodz.bas.err;

public class UnsupportedFeatureException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnsupportedFeatureException() {
        super();
    }

    public UnsupportedFeatureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFeatureException(String message) {
        super(message);
    }

    public UnsupportedFeatureException(Throwable cause) {
        super(cause);
    }

}
