package net.bodz.bas.jdk6compat.jdk7emul;

/**
 * @see java.lang.ReflectiveOperationException
 */
public class ReflectiveOperationException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public ReflectiveOperationException() {
        super();
    }

    public ReflectiveOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectiveOperationException(String message) {
        super(message);
    }

    public ReflectiveOperationException(Throwable cause) {
        super(cause);
    }

}
