package net.bodz.bas.flow;

import java.io.Flushable;
import java.io.IOException;

public interface ISender
        extends Flushable {

    void send(Object data)
            throws IOException;

    @Override
    void flush()
            throws IOException;

}
