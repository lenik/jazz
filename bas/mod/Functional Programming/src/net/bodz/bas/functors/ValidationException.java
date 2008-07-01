package net.bodz.bas.functors;

import net.bodz.bas.lang.FunctorException;


public class ValidationException extends FunctorException {

    private static final long serialVersionUID = 3536130039204718545L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
