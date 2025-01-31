package net.bodz.bas.servlet;

import net.bodz.bas.c.java.io.FileDate;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.OffsetDateTime;

public class FileBlob
        extends AbstractBlob {

    File file;

    public FileBlob(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
        this.filename = file.getName();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String getLocation() {
        return file.toString();
    }

    @Override
    public String getPath() {
        return file.getPath();
    }

    @Override
    public Long getLength()
            throws IOException {
        return file.length();
    }

    @Override
    public OffsetDateTime getLastModified()
            throws IOException {
        long time = file.lastModified();
        return FileDate.toZonedDateTime(time).toOffsetDateTime();
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return Files.newInputStream(file.toPath());
    }

}
