package net.bodz.bas.exceptions;

public class RuntimizedException extends RuntimeException {

    private static final long serialVersionUID = 3749253268735929927L;

    public RuntimizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimizedException(Throwable cause) {
        super(cause);
    }

    public <T extends Throwable> void rethrow(Class<T> type) throws T {
        Throwable cause = getCause();
        try {
            throw type.cast(cause);
        } catch (ClassCastException e) {
            throw new UnexpectedException("EFJG - Enhance the fucking java generics"); //$NON-NLS-1$
            // throw new RareException(this);
        }
    }

}
