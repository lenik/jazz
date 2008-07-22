package net.bodz.mda.parsers.io;

import java.io.IOException;

public interface FilePosition {

    /** current offset, 0-based */
    long tell() throws IOException;

}
