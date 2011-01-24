package net.bodz.bas.jdk6compat.jdk7emul;

/**
 * @see java.lang.IllegalAccessException
 */
public class IllegalAccessException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public IllegalAccessException(java.lang.IllegalAccessException cause) {
        super(cause.getMessage(), cause.getCause());
    }

    public IllegalAccessException() {
        super();
    }

    public IllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessException(String message) {
        super(message);
    }

    public IllegalAccessException(Throwable cause) {
        super(cause);
    }

}
