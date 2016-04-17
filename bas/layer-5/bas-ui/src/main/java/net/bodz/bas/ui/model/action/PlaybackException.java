package net.bodz.bas.ui.model.action;

public class PlaybackException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public PlaybackException() {
        super();
    }

    public PlaybackException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaybackException(String message) {
        super(message);
    }

    public PlaybackException(Throwable cause) {
        super(cause);
    }

}
