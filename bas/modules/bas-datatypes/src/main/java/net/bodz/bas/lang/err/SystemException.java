package net.bodz.bas.lang.err;

public class SystemException extends Exception {

    private static final long serialVersionUID = 3120686750988136459L;

    public SystemException() {
        super();
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

}
