package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.io.res.IStreamResource;

public interface ISeekable
        extends ITellable {

    /**
     * Set absolute position.
     */
    void seek(long position)
            throws IOException;

    IStreamResource crop(long start, long end)
            throws IOException;

}
