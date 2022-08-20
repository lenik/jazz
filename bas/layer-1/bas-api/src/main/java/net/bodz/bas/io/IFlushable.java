package net.bodz.bas.io;

import java.io.Flushable;
import java.io.IOException;

public interface IFlushable
        extends
            Flushable {

    @Override
    default void flush()
            throws IOException {
    }

}
