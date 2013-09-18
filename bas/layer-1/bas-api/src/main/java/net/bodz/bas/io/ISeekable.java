package net.bodz.bas.io;

import java.io.IOException;

public interface ISeekable {

    /**
     * Set absolute position.
     */
    void seek(long position)
            throws IOException;

}
