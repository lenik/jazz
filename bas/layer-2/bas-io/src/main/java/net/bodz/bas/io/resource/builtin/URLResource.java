package net.bodz.bas.io.resource.builtin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import net.bodz.bas.err.UnexpectedException;
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

    /**
     * @param append
     *            Ignored for non-file protocol URL.
     */
    @Override
    protected OutputStream _newOutputStream(boolean append)
            throws IOException {
        switch (url.getProtocol()) {
        case "file":
            File file;
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
            return new FileOutputStream(file, append);

        default:
            return url.openConnection().getOutputStream();
        }
    }

}
