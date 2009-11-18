package net.bodz.bas.jvm.stack;

import net.bodz.bas.api.exceptions.UnexpectedException;

public class FrameConstructException
        extends UnexpectedException {

    private static final long serialVersionUID = 1L;

    public FrameConstructException() {
        super();
    }

    public FrameConstructException( String message, Throwable cause ) {
        super( message, cause );
    }

    public FrameConstructException( String message ) {
        super( message );
    }

    public FrameConstructException( Throwable cause ) {
        super( cause );
    }

}
