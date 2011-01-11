package net.bodz.bas.flow;

import java.io.Flushable;
import java.io.IOException;

public interface IReceiver
        extends Flushable {

    /**
     * no src-port, this is a guideline, so if src-info is required, that info should be encoded in
     * the data.
     */
    void recv(Object data)
            throws IOException;

    @Override
    void flush()
            throws IOException;

}
