package net.bodz.bas.vfs;

import java.io.IOException;

public class FileResolveException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public FileResolveException() {
        super();
    }

    public FileResolveException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileResolveException(String message) {
        super(message);
    }

    public FileResolveException(Throwable cause) {
        super(cause);
    }

}
