package net.bodz.bas.sio;

import java.io.Closeable;
import java.io.IOException;

public interface ICloseable
        extends Closeable {

    @Override
    void close()
            throws IOException;

    boolean isClosed();

}
