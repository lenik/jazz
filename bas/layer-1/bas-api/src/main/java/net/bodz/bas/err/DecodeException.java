package net.bodz.bas.err;

public class DecodeException
        extends CodecException {

    private static final long serialVersionUID = 1L;

    public DecodeException() {
        super();
    }

    public DecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(Throwable cause) {
        super(cause);
    }

}
