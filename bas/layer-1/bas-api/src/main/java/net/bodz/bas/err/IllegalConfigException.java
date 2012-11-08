package net.bodz.bas.err;

public class IllegalConfigException
        extends IllegalUsageException {

    private static final long serialVersionUID = 1L;

    public IllegalConfigException() {
        super();
    }

    public IllegalConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalConfigException(String message) {
        super(message);
    }

    public IllegalConfigException(Throwable cause) {
        super(cause);
    }

}
