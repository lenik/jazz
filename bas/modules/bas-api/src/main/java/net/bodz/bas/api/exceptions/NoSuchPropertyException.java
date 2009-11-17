package net.bodz.bas.api.exceptions;

public class NoSuchPropertyException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String propertyName;

    public NoSuchPropertyException(String propertyName) {
        super(propertyName);
        if (propertyName == null)
            throw new NullPointerException("propertyName");
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

}
