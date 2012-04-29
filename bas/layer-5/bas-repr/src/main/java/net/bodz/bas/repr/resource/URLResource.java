package net.bodz.bas.repr.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLResource
        extends AbstractResource {

    private final URL url;

    public URLResource(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    @Override
    public String getPath() {
        return url.getPath();
    }

    @Override
    public InputStream openBinary()
            throws IOException {
        return url.openStream();
    }

}
