package net.bodz.bas.ar;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public interface IArchiver
        extends Closeable {

    OutputStream putNextEntry(IArchiveEntry entry)
            throws IOException;

    void closeEntry()
            throws IOException;

}
