package net.bodz.bas.err;

public class RuntimizedException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RuntimizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimizedException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public <T extends Throwable> void rethrow(Class<T> type)
            throws T {
        Throwable cause = getCause();
        try {
            throw type.cast(cause);
        } catch (ClassCastException e) {
            throw new UnexpectedException("EFJG - Enhance the fucking java generics");
            // throw new RareException(this);
        }
    }

}
