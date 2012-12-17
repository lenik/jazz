package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.JavaioStreamResource;

public class URLResource
        extends JavaioStreamResource {

    private final URL url;

    public URLResource(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    public URLResource(URL url, Charset charset) {
        this(url);
        this.setCharset(charset);
    }

    public URLResource(URL url, String charsetName) {
        this(url);
        this.setCharset(charsetName);
    }

    public URLResource(String url)
            throws MalformedURLException {
        this(new URL(url));
    }

    public URLResource(String url, Charset charset)
            throws MalformedURLException {
        this(new URL(url), charset);
    }

    public URLResource(String url, String charsetName)
            throws MalformedURLException {
        this(new URL(url), charsetName);
    }

    public URL getURL() {
        return url;
    }

    @Override
    protected InputStream _newInputStream()
            throws IOException {
        return url.openStream();
    }

    @Override
    protected OutputStream _newOutputStream()
            throws IOException {
        if (isAppendMode())
            throw new IOException("Append to a URL resource isn't possible. ");
        return url.openConnection().getOutputStream();
    }

}
