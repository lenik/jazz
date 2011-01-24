package net.bodz.bas.jdk6compat.jdk7emul;

public class ClassNotFoundException
        extends ReflectiveOperationException {

    private static final long serialVersionUID = 1L;

    public ClassNotFoundException(java.lang.ClassNotFoundException cause) {
        super(cause.getMessage(), cause.getCause());
    }

    public ClassNotFoundException() {
        super();
    }

    public ClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassNotFoundException(String message) {
        super(message);
    }

    public ClassNotFoundException(Throwable cause) {
        super(cause);
    }

}
