package net.bodz.bas.servlet;

import java.io.IOException;
import java.time.OffsetDateTime;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public abstract class MutableBlob
        extends AbstractBlob {

    String location;
    String path;
    Long length;
    OffsetDateTime lastModified;

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Long getLength()
            throws IOException {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public OffsetDateTime getLastModified()
            throws IOException {
        return lastModified;
    }

    public void setLastModified(OffsetDateTime lastModified) {
        this.lastModified = lastModified;
    }

}