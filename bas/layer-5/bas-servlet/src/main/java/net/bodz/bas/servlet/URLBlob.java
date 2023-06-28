package net.bodz.bas.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.bodz.bas.c.java.io.FileURL;

public class URLBlob
        extends MutableBlob {

    URL url;
    String filename;

    public URLBlob(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    @Override
    public String getLocation() {
        return url.toString();
    }

    @Override
    public String getPath() {
        return url.getPath();
    }

    @Override
    public Long getLength()
            throws IOException {
        return FileURL.length(url, null);
    }

    @Override
    public Long getLastModified()
            throws IOException {
        return FileURL.lastModified(url, null);
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return url.openStream();
    }

}
