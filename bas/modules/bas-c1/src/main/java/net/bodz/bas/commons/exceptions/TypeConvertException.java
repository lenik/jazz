package net.bodz.bas.commons.exceptions;

public class TypeConvertException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TypeConvertException() {
        super();
    }

    public TypeConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeConvertException(String message) {
        super(message);
    }

    public TypeConvertException(Throwable cause) {
        super(cause);
    }

}
