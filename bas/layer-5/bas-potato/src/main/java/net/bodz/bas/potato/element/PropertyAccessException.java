package net.bodz.bas.potato.element;

class PropertyAccessException
        extends Exception {

    public PropertyAccessException() {
    }

    public PropertyAccessException(Throwable cause) {
        super(cause);
    }

    public PropertyAccessException(String message) {
        super(message);
    }

    public PropertyAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
