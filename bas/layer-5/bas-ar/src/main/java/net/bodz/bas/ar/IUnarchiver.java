package net.bodz.bas.ar;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.ZipFile;

import net.bodz.bas.c.java.util.zip.JuzZipFileUnarchiver;

public interface IUnarchiver
        extends Closeable {

    Iterable<? extends IArchiveEntry> entries()
            throws IOException;

    /**
     * @return <code>null</code> if not found
     * @throws IllegalStateException if closed.
     */
    IArchiveEntry getEntry(String name)
            throws IOException;

    class fn {

        public static IUnarchiver impl(ZipFile zipFile) {
            if (zipFile == null)
                throw new NullPointerException("zipFile");
            return new JuzZipFileUnarchiver(zipFile);
        }

    }

}
