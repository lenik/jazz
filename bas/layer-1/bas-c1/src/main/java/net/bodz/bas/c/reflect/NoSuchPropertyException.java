package net.bodz.bas.c.reflect;

public class NoSuchPropertyException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public NoSuchPropertyException() {
        super();
    }

    public NoSuchPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPropertyException(String message) {
        super(message);
    }

    public NoSuchPropertyException(Throwable cause) {
        super(cause);
    }

}
