package net.bodz.bas.sio;

public class SIOException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SIOException() {
        super();
    }

    public SIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public SIOException(String message) {
        super(message);
    }

    public SIOException(Throwable cause) {
        super(cause);
    }

}
