package net.bodz.bas.c.java.util.zip;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.OpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.adapter.InputStreamByteIn;
import net.bodz.bas.io.adapter.ReaderCharIn;
import net.bodz.bas.io.res.AbstractStreamInputSource;

class ZipFileArchiveEntry
        extends AbstractStreamInputSource
        implements IArchiveEntry {

    private final ZipFile zipFile;
    private final ZipEntry zipEntry;

    public ZipFileArchiveEntry(ZipFile zipFile, ZipEntry zipEntry) {
        if (zipFile == null)
            throw new NullPointerException("zipFile");
        if (zipEntry == null)
            throw new NullPointerException("zipEntry");
        this.zipFile = zipFile;
        this.zipEntry = zipEntry;
    }

    @Override
    public String getName() {
        return zipEntry.getName();
    }

    @Override
    public boolean isDirectory() {
        return zipEntry.isDirectory();
    }

    @Override
    public long getCreatedTime() {
        return zipEntry.getTime();
    }

    @Override
    public long getModifiedTime() {
        return zipEntry.getTime();
    }

    @Override
    public long getSize() {
        return zipEntry.getSize();
    }

    @Override
    public long getArchivedSize() {
        return zipEntry.getCompressedSize();
    }

    @Override
    public int getMode() {
        return defaultMode;
    }

    @Override
    public String getOwner() {
        return null;
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getComment() {
        return zipEntry.getComment();
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        InputStream in = zipFile.getInputStream(zipEntry);
        return in;
    }

    @Override
    protected Reader _newReader(OpenOption... options)
            throws IOException {
        InputStream in = _newInputStream(options);
        return new InputStreamReader(in, getCharset());
    }

    @Override
    protected IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        InputStream in = _newInputStream(options);
        return new InputStreamByteIn(in);
    }

    @Override
    protected ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        Reader reader = _newReader(options);
        return new ReaderCharIn(reader);
    }

}
