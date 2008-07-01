package net.bodz.bas.lang.err;

public class RuntimizedException extends RuntimeException {

    private static final long serialVersionUID = 3749253268735929927L;

    public RuntimizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimizedException(Throwable cause) {
        super(cause);
    }

    @SuppressWarnings("unchecked")
    public <T extends Throwable> void rethrow() throws T {
        Throwable cause = getCause();
        try {
            throw (T) cause;
        } catch (ClassCastException e) {
            throw new UnexpectedException(
                    "EFJG - Enhance the fucking java generics");
            // throw new RareException(this);
        }
    }

}
