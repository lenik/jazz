package net.bodz.swt.draw.dev;

public class DrawException
        extends RuntimeException {

    static final long serialVersionUID = -627622313138496918L;

    public DrawException() {
        super();
    }

    public DrawException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrawException(String message) {
        super(message);
    }

    public DrawException(Throwable cause) {
        super(cause);
    }

}
