package net.bodz.bas.mf.std;

public class ValidationException
        extends TypeSystemException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException() {
        super();
    }

}
