package net.bodz.bas.lang.util;

import net.bodz.bas.lang.err.UnexpectedException;

public class FrameConstructException extends UnexpectedException {

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
