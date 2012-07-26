package net.bodz.bas.err;

public class IllegalEnvironConfigException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IllegalEnvironConfigException() {
        super();
    }

    public IllegalEnvironConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEnvironConfigException(String message) {
        super(message);
    }

    public IllegalEnvironConfigException(Throwable cause) {
        super(cause);
    }

}
