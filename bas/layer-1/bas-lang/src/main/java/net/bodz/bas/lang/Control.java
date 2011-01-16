package net.bodz.bas.lang;

public class Control
        extends Error {

    private static final long serialVersionUID = 1L;

    public Control() {
        super();
    }

    public Control(String message, Throwable cause) {
        super(message, cause);
    }

    public Control(String message) {
        super(message);
    }

    public Control(Throwable cause) {
        super(cause);
    }

}
