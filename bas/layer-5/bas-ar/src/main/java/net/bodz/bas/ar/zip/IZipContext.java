package net.bodz.bas.ar.zip;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ITellable;

public interface IZipContext
        extends ITellable, ICroppable {

    Charset getZipCharset();

    long getZipLength();

    void requireZipVersion(short version);

    void reloadLFH(ZipEntry entry)
            throws IOException;

}
