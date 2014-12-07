package net.bodz.bas.io;

import java.io.Closeable;
import java.io.IOException;

public interface ICloseable
        extends Closeable {

    void close()
            throws IOException;

    boolean isClosed();

}
