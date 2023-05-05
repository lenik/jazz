package net.bodz.bas.err;

public class IllegalUsageException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final ExceptionSupplier<IllegalUsageException> SUPPLIER = //
            (String s, Throwable cause) -> new IllegalUsageException(s);

    public IllegalUsageException() {
        super();
    }

    public IllegalUsageException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUsageException(String message) {
        super(message);
    }

    public IllegalUsageException(Throwable cause) {
        super(cause);
    }

}
