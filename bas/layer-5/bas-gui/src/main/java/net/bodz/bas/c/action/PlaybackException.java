package net.bodz.bas.c.action;

public class PlaybackException
        extends Exception {

    private static final long serialVersionUID = 5079299543033517264L;

    private PlaybackException() {
        super();
    }

    private PlaybackException(String message, Throwable cause) {
        super(message, cause);
    }

    private PlaybackException(String message) {
        super(message);
    }

    private PlaybackException(Throwable cause) {
        super(cause);
    }

}
