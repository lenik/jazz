package net.bodz.bas.vfs.path;

import java.io.IOException;

public class BadPathException
        extends IOException {

    private static final long serialVersionUID = 1L;

    public BadPathException(String path) {
        super(path);
    }

    public BadPathException(String message, String path) {
        super(message + ": " + path);
    }

}
