package net.bodz.bas.jvm.stack;

public class FrameConstructError
        extends Error {

    private static final long serialVersionUID = 1L;

    public FrameConstructError() {
        super();
    }

    public FrameConstructError(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameConstructError(String message) {
        super(message);
    }

    public FrameConstructError(Throwable cause) {
        super(cause);
    }

}
