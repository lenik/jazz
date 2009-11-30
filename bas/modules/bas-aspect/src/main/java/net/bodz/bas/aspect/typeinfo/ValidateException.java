package net.bodz.bas.aspect.typeinfo;

import net.bodz.bas.aspect.AspectException;

public class ValidateException
        extends AspectException {

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
