package net.bodz.bas.c.java.util.zip;

import java.io.IOException;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.ar.IUnarchiver;
import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.t.iterator.Iterators;

public class ZipFileUnarchiver
        implements IUnarchiver {

    private ZipFile zipFile;
    private boolean closed;

    public ZipFileUnarchiver(ZipFile zipFile) {
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
    public IArchiveEntry getEntry(String name) {
        if (closed)
            throw new IllegalStateException("already closed.");
        ZipEntry entry = zipFile.getEntry(name);
        if (entry == null)
            return null;
        else
            return new ZipFileArchiveEntry(zipFile, entry);
    }

    @Override
    public Iterable<? extends IArchiveEntry> entries() {
        return new Iterable<IArchiveEntry>() {
            @Override
            public Iterator<IArchiveEntry> iterator() {
                if (closed)
                    throw new IllegalStateException("already closed.");
                else
                    return Iterators.transform(zipFile.entries(), transformer);
            }
        };
    }

    class EntryTransformer
            implements ITransformer<ZipEntry, IArchiveEntry> {

        @Override
        public ZipFileArchiveEntry transform(ZipEntry input)
                throws TransformException {
            return new ZipFileArchiveEntry(zipFile, input);
        }

    }

    final EntryTransformer transformer = new EntryTransformer();

}
