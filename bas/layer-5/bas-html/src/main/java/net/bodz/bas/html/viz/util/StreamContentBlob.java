package net.bodz.bas.html.viz.util;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.repr.content.IStreamContent;
import net.bodz.bas.servlet.MutableBlob;
import net.bodz.bas.std.rfc.mime.ContentType;

public class StreamContentBlob
        extends MutableBlob {

    IStreamContent content;

    public StreamContentBlob(IStreamContent content) {
        if (content == null)
            throw new NullPointerException("content");
        this.content = content;
    }

    @Override
    public String getFilename() {
        return content.getFileName();
    }

    @Override
    public ContentType getContentType() {
        return content.getContentType();
    }

    @Override
    public String getEncoding() {
        return content.getEncoding();
    }

    @Override
    public Long getLength()
            throws IOException {
        return content.getContentLength();
    }

    @Override
    public Long getLastModified()
            throws IOException {
        return content.getLastModified();
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return content.newInputStream();
    }

}
