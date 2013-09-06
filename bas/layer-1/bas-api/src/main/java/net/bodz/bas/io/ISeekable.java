package net.bodz.bas.io;

import java.io.IOException;

public interface ISeekable {

    void seek(long position)
            throws IOException;

    void seek(long position, int relative)
            throws IOException;

}
