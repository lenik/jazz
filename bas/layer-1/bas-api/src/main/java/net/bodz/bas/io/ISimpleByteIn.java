package net.bodz.bas.io;

import java.io.IOException;

public interface ISimpleByteIn {

    /**
     * @return The read byte, in the range of 0 to 255. Returns -1 if the EOF is reached.
     */
    int read()
            throws IOException;

}
