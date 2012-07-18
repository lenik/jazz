package net.bodz.bas.err;

import java.io.IOException;

public class RuntimeIOException
        extends RuntimizedException {

    private static final long serialVersionUID = 1L;

    public RuntimeIOException(String message, IOException cause) {
        super(message, cause);
    }

    public RuntimeIOException(IOException cause) {
        super(cause);
    }

    @Override
    public synchronized IOException getCause() {
        return (IOException) super.getCause();
    }

}
