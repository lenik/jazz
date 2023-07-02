package net.bodz.lily.entity.type;

import net.bodz.bas.err.IllegalUsageException;

public class NoIdentifierException
        extends IllegalUsageException {

    private static final long serialVersionUID = 1L;

    public NoIdentifierException() {
        super();
    }

    public NoIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIdentifierException(String message) {
        super(message);
    }

    public NoIdentifierException(Throwable cause) {
        super(cause);
    }

}
