package net.bodz.bas.make;

public class RealizeException
        extends MakeException {

    public RealizeException() {
    }

    public RealizeException(String message) {
        super(message);
    }

    public RealizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RealizeException(Throwable cause) {
        super(cause);
    }

}
