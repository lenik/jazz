package net.bodz.geom.base;

public class ReadOnlyAttributeException
        extends RuntimeException {

    static final long serialVersionUID = 2237834200964798481L;

    public ReadOnlyAttributeException(Class<?> clazz, String attributeName) {
        super(clazz.getName() + "::" + attributeName + " is read-only. ");
    }

    public ReadOnlyAttributeException(Object object, String attributeName) {
        this(object.getClass(), attributeName);
    }

}
