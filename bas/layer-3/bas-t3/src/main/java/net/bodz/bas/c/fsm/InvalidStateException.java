package net.bodz.bas.c.fsm;

public class InvalidStateException
        extends RuntimeException {

    static final long serialVersionUID = -1082822725269575429L;

    public InvalidStateException() {
        super();
    }

    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(Throwable cause) {
        super(cause);
    }

}
