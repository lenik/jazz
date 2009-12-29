package net.bodz.bas.type;


public class ValidateException
        extends TypeInfoException {

    private static final long serialVersionUID = 1L;

    public ValidateException() {
        super();
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

}
