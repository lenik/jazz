package net.bodz.bas.io;

import java.io.IOException;

public interface ISimpleCharIn {

    /**
     * @return The read character, which is in the range of 0..65535. Returns -1 if the EOF was
     *         reached.
     */
    int read()
            throws IOException;

}
