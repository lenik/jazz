package net.bodz.bas.jdk6compat.jdk7emul;

/**
 * @see java.lang.InstantiationException
 */
public class InstantiationException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public InstantiationException(java.lang.InstantiationException cause) {
        super(cause.getMessage(), cause.getCause());
    }

    public InstantiationException() {
        super();
    }

    public InstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstantiationException(String message) {
        super(message);
    }

    public InstantiationException(Throwable cause) {
        super(cause);
    }

}
