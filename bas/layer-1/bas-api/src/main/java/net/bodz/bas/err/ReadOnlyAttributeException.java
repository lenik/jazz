package net.bodz.bas.err;

public class ReadOnlyAttributeException
        extends ReadOnlyException {

    static final long serialVersionUID = 1L;

    public ReadOnlyAttributeException(Class<?> clazz, String attributeName) {
        super(clazz.getName() + "::" + attributeName + " is read-only. ");
    }

    public ReadOnlyAttributeException(Object object, String attributeName) {
        this(object.getClass(), attributeName);
    }

}
