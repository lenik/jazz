package net.bodz.bas.ar;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.ICloseable;

public interface IArchiver
        extends ICloseable {

    OutputStream putNextEntry(IArchiveEntry entry)
            throws IOException;

    void closeEntry()
            throws IOException;

}
