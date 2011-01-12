package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import net.bodz.bas.io.resource.JavaioStreamResource;

public class URLResource
        extends JavaioStreamResource {

    private final URL url;

    public URLResource(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    public URL getURL() {
        return url;
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        return url.openStream();
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        if (isAppendMode())
            throw new IOException("Append to a URL resource isn't possible. ");
        return url.openConnection().getOutputStream();
    }

}
