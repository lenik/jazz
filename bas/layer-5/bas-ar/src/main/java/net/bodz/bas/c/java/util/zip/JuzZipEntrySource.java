package net.bodz.bas.c.java.util.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.OpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.io.res.AbstractInputStreamSource;
import net.bodz.bas.meta.decl.NotNull;

public class JuzZipEntrySource
        extends AbstractInputStreamSource<JuzZipEntrySource> {

    private final ZipFile zipFile;
    private final ZipEntry zipEntry;

    public JuzZipEntrySource(ZipFile zipFile, ZipEntry zipEntry) {
        if (zipFile == null)
            throw new NullPointerException("zipFile");
        if (zipEntry == null)
            throw new NullPointerException("zipEntry");
        this.zipFile = zipFile;
        this.zipEntry = zipEntry;
    }

    @Override
    public String getName() {
        String path = zipEntry.getName();
        String baseName = FilePath.getBaseName(path);
        return baseName;
    }

    @Override
    public String getPath() {
        return zipEntry.getName();
    }

    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        InputStream in = zipFile.getInputStream(zipEntry);
        return in;
    }

}
