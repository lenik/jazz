package net.bodz.bas.dbi;

public class RuntimeDataAccessException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RuntimeDataAccessException() {
        super();
    }

    public RuntimeDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeDataAccessException(String message) {
        super(message);
    }

    public RuntimeDataAccessException(Throwable cause) {
        super(cause);
    }

}
