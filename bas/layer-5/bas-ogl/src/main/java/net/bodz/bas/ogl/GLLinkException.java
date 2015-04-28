package net.bodz.bas.ogl;

import com.jogamp.opengl.GLException;

public class GLLinkException
        extends GLException {

    private static final long serialVersionUID = 1L;

    public GLLinkException() {
        super();
    }

    public GLLinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public GLLinkException(String message) {
        super(message);
    }

    public GLLinkException(Throwable cause) {
        super(cause);
    }

}
