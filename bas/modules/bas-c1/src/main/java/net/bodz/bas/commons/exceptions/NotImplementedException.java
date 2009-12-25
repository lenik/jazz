package net.bodz.bas.commons.exceptions;

public class NotImplementedException extends UnsupportedOperationException {

    private static final long serialVersionUID = 5315693899871858268L;

    public NotImplementedException() {
        super();
    }

    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementedException(String message) {
        super(message);
    }

    public NotImplementedException(Throwable cause) {
        super(cause);
    }

}
