package net.bodz.bas.io.res.builtin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.res.AbstractIOStreamResource;

public class URLResource
        extends AbstractIOStreamResource {

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

// @Override
// public long getLength() {
// switch (url.getProtocol()) {
// case "file":
// File file = FileURL.toFile(url, null);
// if (file != null)
// return file.length();
// else
// return -1L;
// default:
// return -1L;
// }
// }

    @Override
    protected InputStream _newInputStream(OpenOption... options)
            throws IOException {
        return url.openStream();
    }

    /**
     * @param append
     *            Ignored for non-file protocol URL.
     */
    @Override
    protected OutputStream _newOutputStream(OpenOption... options)
            throws IOException {

        boolean append = OpenOptions.isAppend(options);

        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url, null);
            if (file == null)
                throw new IOException("Illegal file URL: " + url);
            else
                return new FileOutputStream(file, append);

        default:
            return url.openConnection().getOutputStream();
        }
    }

    public long lastModified() {
        return lastModified(-1L);
    }

    public long lastModified(long fallback) {
        long lastModified = fallback;
        File file = FileURL.toNearestFile(url);
        if (file != null)
            lastModified = file.lastModified();
        return lastModified;
    }

}
