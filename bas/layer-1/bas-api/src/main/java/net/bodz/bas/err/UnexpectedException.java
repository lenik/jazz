package net.bodz.bas.err;

public class UnexpectedException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final ExceptionSupplier<UnexpectedException> SUPPLIER = //
            (String s, Throwable cause) -> new UnexpectedException(s, cause);

    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

}
