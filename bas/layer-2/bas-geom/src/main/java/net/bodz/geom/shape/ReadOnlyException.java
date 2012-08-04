package net.bodz.geom.shape;

public class ReadOnlyException
        extends RuntimeException {

    static final long serialVersionUID = 2237834200964798481L;

    public ReadOnlyException() {
        super();
    }

    public ReadOnlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadOnlyException(String message) {
        super(message);
    }

    public ReadOnlyException(Throwable cause) {
        super(cause);
    }

    public ReadOnlyException(Class<?> clazz, String attributeName) {
        this(clazz.getName() + "::" + attributeName + " is read-only. ");
    }

    public ReadOnlyException(Object object, String attributeName) {
        this(object.getClass(), attributeName);
    }
}
