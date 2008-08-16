package net.bodz.bas.lang.err;

public class ReflectException extends RuntimeException {

    private static final long serialVersionUID = 1644028224018010443L;

    public ReflectException() {
        super();
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }

}
