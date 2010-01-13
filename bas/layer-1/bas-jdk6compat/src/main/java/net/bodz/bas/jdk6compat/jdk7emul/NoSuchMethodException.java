package net.bodz.bas.jdk6compat.jdk7emul;

/**
 * @see java.lang.NoSuchMethodException
 */
public class NoSuchMethodException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public NoSuchMethodException() {
        super();
    }

    public NoSuchMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchMethodException(String message) {
        super(message);
    }

    public NoSuchMethodException(Throwable cause) {
        super(cause);
    }

}
