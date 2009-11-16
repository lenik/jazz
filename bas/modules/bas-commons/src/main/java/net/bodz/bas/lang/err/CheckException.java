package net.bodz.bas.lang.err;

public class CheckException extends Exception {

    private static final long serialVersionUID = -7500685030093882350L;

    public CheckException() {
        super();
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

}
