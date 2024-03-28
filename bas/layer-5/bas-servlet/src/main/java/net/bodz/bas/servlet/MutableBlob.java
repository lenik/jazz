package net.bodz.bas.servlet;

import java.io.IOException;
import java.time.OffsetDateTime;

import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.ContentTypes;

public abstract class MutableBlob
        implements
            IBlob {

    String location;
    String path;
    String filename;
    ContentType contentType = ContentTypes.application_octet_stream;
    String encoding;
    Long length;
    OffsetDateTime lastModified;
    String description;

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
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.encoding = contentEncoding;
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

    @Override
    public String getDescription()
            throws IOException {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}