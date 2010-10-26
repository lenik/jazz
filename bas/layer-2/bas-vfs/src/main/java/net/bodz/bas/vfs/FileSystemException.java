package net.bodz.bas.vfs;

import java.io.IOException;

public class FileSystemException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public FileSystemException() {
        super();
    }

    public FileSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSystemException(String message) {
        super(message);
    }

    public FileSystemException(Throwable cause) {
        super(cause);
    }

}
