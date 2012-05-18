package net.bodz.bas.err;

/**
 * Failed to load.
 * 
 * Used in loading processes, like lazy-initialize, etc.
 */
public class LazyLoadException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LazyLoadException() {
        super();
    }

    public LazyLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LazyLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public LazyLoadException(String message) {
        super(message);
    }

    public LazyLoadException(Throwable cause) {
        super(cause);
    }

}
