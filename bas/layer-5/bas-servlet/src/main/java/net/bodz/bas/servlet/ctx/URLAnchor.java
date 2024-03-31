package net.bodz.bas.servlet.ctx;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.err.UnexpectedException;

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
    public URLAnchor enter() {
        if (directory)
            return this;

        String s = url.toString();
        String qs = null;
        int ques = s.lastIndexOf('?');
        if (ques != -1) {
            qs = s.substring(ques);
            s = s.substring(0, ques);
        }

        s += "/";
        if (qs != null)
            s += qs;
        try {
            URL newUrl = new URL(s);
            return new URLAnchor(newUrl);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
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
