package net.bodz.bas.c.java.util.zip;

import java.nio.charset.Charset;
import java.util.zip.ZipFile;

import net.bodz.bas.ar.AbstractArchiveEntry;
import net.bodz.bas.ar.IArchiveEntry;
import net.bodz.bas.io.res.IStreamInputSource;

class JuzZipEntry
        extends AbstractArchiveEntry
        implements IArchiveEntry {

    private final ZipFile zipFile;
    private final java.util.zip.ZipEntry zipEntry;

    public JuzZipEntry(ZipFile zipFile, java.util.zip.ZipEntry zipEntry) {
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
    public String getComment() {
        return zipEntry.getComment();
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        JuzZipEntrySource src = new JuzZipEntrySource(zipFile, zipEntry);
        src.setCharset(charset);
        return src;
    }

}
