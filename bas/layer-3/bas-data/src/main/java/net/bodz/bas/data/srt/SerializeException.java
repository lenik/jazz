package net.bodz.bas.data.srt;

public class SerializeException
        extends Exception {

    private static final long serialVersionUID = 9182248134717166976L;

    public SerializeException() {
        super();
    }

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(Throwable cause) {
        super(cause);
    }

}
