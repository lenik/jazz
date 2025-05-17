package net.bodz.bas.potato.element;

public class PropertyReadException
        extends PropertyAccessException {

    public PropertyReadException() {
    }

    public PropertyReadException(Throwable cause) {
        super(cause);
    }

    public PropertyReadException(String message) {
        super(message);
    }

    public PropertyReadException(String message, Throwable cause) {
        super(message, cause);
    }

}
