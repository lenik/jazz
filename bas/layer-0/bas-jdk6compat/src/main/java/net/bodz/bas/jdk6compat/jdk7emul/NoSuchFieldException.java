package net.bodz.bas.jdk6compat.jdk7emul;

/**
 * @see java.lang.NoSuchFieldException
 */
public class NoSuchFieldException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public NoSuchFieldException(java.lang.NoSuchFieldException cause) {
        super(cause.getMessage(), cause.getCause());
    }

    public NoSuchFieldException() {
        super();
    }

    public NoSuchFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchFieldException(String message) {
        super(message);
    }

    public NoSuchFieldException(Throwable cause) {
        super(cause);
    }

}
