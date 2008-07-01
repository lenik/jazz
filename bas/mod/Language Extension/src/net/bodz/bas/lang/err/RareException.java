package net.bodz.bas.lang.err;

/**
 * <I>"Failed to Re-assemble the RuntimizedException"</I> Exception
 */
public class RareException extends RuntimeException {

    private static final long serialVersionUID = -4063333676371729667L;

    public RareException() {
        super();
    }

    public RareException(String message, Throwable cause) {
        super(message, cause);
    }

    public RareException(String message) {
        super(message);
    }

    public RareException(Throwable cause) {
        super(cause);
    }

    // Because of the BUG/EFJG, we can throw a Non-RuntimeException to fool with
    // the java compiler
    public static void _Throw(Throwable t) {
        throw (RuntimeException) t;
    }

}
