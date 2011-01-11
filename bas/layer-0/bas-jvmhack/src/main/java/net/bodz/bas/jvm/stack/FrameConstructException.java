package net.bodz.bas.jvm.stack;

public class FrameConstructException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public FrameConstructException() {
        super();
    }

    public FrameConstructException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameConstructException(String message) {
        super(message);
    }

    public FrameConstructException(Throwable cause) {
        super(cause);
    }

}
