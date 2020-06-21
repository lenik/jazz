package net.bodz.bas.c.java.util.zip;

import java.io.IOException;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.IUnarchiver;
import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.AbstractTransformer;
import net.bodz.bas.t.iterator.Iterators;

public class JuzZipFileUnarchiver
        implements IUnarchiver {

    private ZipFile zipFile;
    private boolean closed;

    public JuzZipFileUnarchiver(ZipFile zipFile) {
        if (zipFile == null)
            throw new NullPointerException("zipFile");
        this.zipFile = zipFile;
    }

    @Override
    public void close()
            throws IOException {
        if (!closed) {
            zipFile.close();
            closed = true;
        }
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public Iterable<JuzZipEntry> entries() {
        return new Iterable<JuzZipEntry>() {
            @Override
            public Iterator<JuzZipEntry> iterator() {
                if (closed)
                    throw new IllegalStateException("already closed.");
                else
                    return Iterators.transform(zipFile.entries(), transformer);
            }
        };
    }

    @Override
    public IArchiveEntry getEntry(String name) {
        if (closed)
            throw new IllegalStateException("already closed.");
        ZipEntry entry = zipFile.getEntry(name);
        if (entry == null)
            return null;
        else
            return new JuzZipEntry(zipFile, entry);
    }

    class EntryTransformer
            extends AbstractTransformer<ZipEntry, JuzZipEntry> {

        @Override
        public JuzZipEntry transform(ZipEntry input)
                throws TransformException {
            return new JuzZipEntry(zipFile, input);
        }

    }

    final EntryTransformer transformer = new EntryTransformer();

}
