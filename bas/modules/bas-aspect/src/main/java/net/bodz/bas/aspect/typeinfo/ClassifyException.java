package net.bodz.bas.aspect.typeinfo;

import net.bodz.bas.aspect.AspectException;

public class ClassifyException
        extends AspectException {

    private static final long serialVersionUID = 1L;

    public ClassifyException() {
        super();
    }

    public ClassifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassifyException(String message) {
        super(message);
    }

    public ClassifyException(Throwable cause) {
        super(cause);
    }

}
