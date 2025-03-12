package net.bodz.bas.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;

import net.bodz.bas.io.res.IStreamInputSource;

public class StreamBlob
        extends AbstractBlob {

    IStreamInputSource source;

    public StreamBlob(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
        this.filename = source.getName();
        this.charset = source.getCharset();
    }

    @Override
    public String getLocation() {
        return source.getPath();
    }

    @Override
    public String getPath() {
        return source.getPath();
    }

    @Override
    public Long getLength()
            throws IOException {
        return source.getLength();
    }

    @Override
    public OffsetDateTime getLastModified()
            throws IOException {
        return source.getLastModified();
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return source.newInputStream();
    }

}
