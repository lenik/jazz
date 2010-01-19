package net.bodz.bas.sio;

import java.io.IOException;

public interface ISimpleCharOut {

    /**
     * @param ch
     *            The character to be written is contained in the 16 low-order bits of the given
     *            integer value; the 16 high-order bits are ignored.
     */
    void write(int ch)
            throws IOException;

}
