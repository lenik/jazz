package net.bodz.bas.err;

public class CodecException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CodecException() {
        super();
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodecException(String message) {
        super(message);
    }

    public CodecException(Throwable cause) {
        super(cause);
    }

}
