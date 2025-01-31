package net.bodz.bas.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.io.FileDate;

public class PathBlob
        extends AbstractBlob {

    Path path;

    public PathBlob(Path path) {
        if (path == null)
            throw new NullPointerException("path");
        this.path = path;
        this.filename = path.getFileName().toString();
    }

    @Override
    public String getLocation() {
        return path.toString();
    }

    @Override
    public String getPath() {
        return path.toString();
    }

    @Override
    public Long getLength()
            throws IOException {
        return Files.size(path);
    }

    @Override
    public OffsetDateTime getLastModified()
            throws IOException {
        FileTime fileTime = Files.getLastModifiedTime(path);
        return FileDate.toZonedDateTime(fileTime).toOffsetDateTime();
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return Files.newInputStream(path);
    }

}
