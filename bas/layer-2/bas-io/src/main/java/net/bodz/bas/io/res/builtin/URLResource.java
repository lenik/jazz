package net.bodz.bas.io.res.builtin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.io.res.AbstractIOStreamResource;
import net.bodz.bas.meta.decl.NotNull;

public class URLResource
        extends AbstractIOStreamResource<URLResource> {

    private final URL url;

    public URLResource(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
    }

    public URLResource(String url)
            throws MalformedURLException {
        this(new URL(url));
    }

    public URL getURL() {
        return url;
    }

    @Override
    public String getPath() {
        return url.getPath();
    }

    @Override
    public Long getLength() {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                if (file != null)
                    return file.length();
                else
                    return null;
            default:
                return null;
        }
    }

    @NotNull
    @Override
    public InputStream newInputStream(OpenOption... options)
            throws IOException {
        return url.openStream();
    }

    @NotNull
    @Override
    public OutputStream newOutputStream(OpenOption... options)
            throws IOException {
        String protocol = url.getProtocol();
        if ("file".equals(protocol)) {
            try {
                URI uri = url.toURI();
                Path path = Paths.get(uri);
                return Files.newOutputStream(path, options);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("invalid url: " + url, e);
            }
        }
        return url.openConnection().getOutputStream();
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
