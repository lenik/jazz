package net.bodz.bas.gui.draw_f.dc;

public class DrawException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

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
