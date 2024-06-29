package net.bodz.bas.c.util;

public class ClassNotSupportException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public ClassNotSupportException() {
        super();
    }

    public ClassNotSupportException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public ClassNotSupportException(String message) {
        super(message);
    }

    public ClassNotSupportException(Throwable rootCause) {
        super(rootCause);
    }

}
