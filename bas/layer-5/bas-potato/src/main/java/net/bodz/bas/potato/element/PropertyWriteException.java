package net.bodz.bas.potato.element;

public class PropertyWriteException
        extends PropertyAccessException {

    public PropertyWriteException() {
    }

    public PropertyWriteException(Throwable cause) {
        super(cause);
    }

    public PropertyWriteException(String message) {
        super(message);
    }

    public PropertyWriteException(String message, Throwable cause) {
        super(message, cause);
    }

}
