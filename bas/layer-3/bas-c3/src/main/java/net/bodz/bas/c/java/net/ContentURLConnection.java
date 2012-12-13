package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.std.rfc.mime.IContent;

class ContentURLConnection
        extends URLConnection {

    private final IContent content;

    public ContentURLConnection(URL url, IContent content) {
        super(url);
        if (content == null)
            throw new NullPointerException("content");
        this.content = content;
    }

    @Override
    public Object getContent()
            throws IOException {
        return content;
    }

    @Override
    public String getContentType() {
        ContentType contentType = content.getContentType();
        return contentType.getName();
    }

    @Override
    public long getContentLengthLong() {
        Long length = content.getContentLength();
        if (length == null)
            return -1;
        else
            return length.longValue();
    }

    @Override
    public void connect()
            throws IOException {
    }

    @Override
    public InputStream getInputStream()
            throws IOException {
        IStreamInputSource source = content.getInputSource();
        return source.newInputStream();
    }

}