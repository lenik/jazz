package net.bodz.bas.io;

import java.io.IOException;

public interface ISeekable
        extends ITellable {

    /**
     * Set absolute position.
     */
    void seek(long position)
            throws IOException;

    long length()
            throws IOException;

}
