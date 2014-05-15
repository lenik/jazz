package net.bodz.bas.http.ctx;

import java.net.MalformedURLException;
import java.net.URL;

public class URLAnchor
        extends AbstractAnchor {

    private URL url;
    private boolean directory;

    public URLAnchor(String url)
            throws MalformedURLException {
        this(new URL(url));
    }

    public URLAnchor(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
        this.directory = this.url.getPath().endsWith("/");
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public String absoluteHref() {
        return url.toString();
    }

    @Override
    public String hrefFrom(String otherPath) {
        return absoluteHref();
    }

    @Override
    public IAnchor join(String spec) {
        try {
            URL newURL = new URL(url, spec);
            return new URLAnchor(newURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return url.toString();
    }

}
