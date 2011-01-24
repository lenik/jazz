package net.bodz.bas.jdk6compat.jdk7emul;

public class InvocationTargetException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public InvocationTargetException(java.lang.reflect.InvocationTargetException cause) {
        super(cause.getMessage(), cause.getCause());
    }

    public InvocationTargetException() {
        super();
    }

    public InvocationTargetException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvocationTargetException(String message) {
        super(message);
    }

    public InvocationTargetException(Throwable cause) {
        super(cause);
    }

}
