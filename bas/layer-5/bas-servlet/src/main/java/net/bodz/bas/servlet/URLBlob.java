package net.bodz.bas.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.io.FileDate;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.std.rfc.mime.ContentType;

public class URLBlob
        extends AbstractBlob {

    URL url;

    public URLBlob(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;

        String path = url.getPath();
        String baseName = FilePath.getBaseName(path);
        this.filename = baseName;
    }

    public URL getURL() {
        return url;
    }

    public void setURL(URL url) {
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
    public OffsetDateTime getLastModified()
            throws IOException {
        Long time = FileURL.lastModified(url, null);
        if (time == null)
            return null;
        else
            return FileDate.toZonedDateTime(time).toOffsetDateTime();
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return url.openStream();
    }

}
