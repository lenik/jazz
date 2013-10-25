package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.IArchiver;

public class ZipArchiver
        extends ZipEngine
        implements IArchiver {

    public ZipArchiver(OutputStream out) {
        super();
    }

    @Override
    public OutputStream putNextEntry(IArchiveEntry entry)
            throws IOException {
        return null;
    }

    @Override
    public void closeEntry()
            throws IOException {
    }

}
