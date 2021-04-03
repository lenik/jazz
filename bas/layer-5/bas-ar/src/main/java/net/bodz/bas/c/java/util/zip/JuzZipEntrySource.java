package net.bodz.bas.c.java.util.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.OpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.io.res.AbstractInputStreamSource;

public class JuzZipEntrySource
        extends AbstractInputStreamSource {

    private ZipFile zipFile;
    private ZipEntry zipEntry;

    public JuzZipEntrySource(ZipFile zipFile, ZipEntry zipEntry) {
        if (zipFile == null)
            throw new NullPointerException("zipFile");
        if (zipEntry == null)
            throw new NullPointerException("zipEntry");
        this.zipFile = zipFile;
        this.zipEntry = zipEntry;
    }

    @Override
    public boolean isCharInPreferred() {
        return false;
    }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        InputStream in = zipFile.getInputStream(zipEntry);
        return in;
    }

}
