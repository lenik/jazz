package net.bodz.bas.ar.zip;

import net.bodz.bas.ar.IArchiveEntry;

public interface IZipEntry
        extends IArchiveEntry, IZipConsts {

    int getMethod();

    int getCrc32();

    long getOffset();

}
