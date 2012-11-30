package net.bodz.bas.err;

public class DuplicatedKeyException
        extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public DuplicatedKeyException() {
        super();
    }

    public DuplicatedKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedKeyException(String s) {
        super(s);
    }

    public DuplicatedKeyException(Throwable cause) {
        super(cause);
    }

}
