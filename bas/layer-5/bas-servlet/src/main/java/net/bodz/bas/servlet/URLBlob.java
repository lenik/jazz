package net.bodz.bas.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.std.rfc.mime.ContentType;

public class URLBlob
        implements
            IBlob {

    URL url;
    String filename;
    String description;
    ContentType contentType = null;

    public URLBlob(URL url) {
        if (url == null)
            throw new NullPointerException("url");
        this.url = url;
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
    public String getFilename() {
        if (filename == null) {
            String path = url.getPath();
            String baseName = FilePath.getBaseName(path);
            return baseName;
        } else {
            return filename;
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public ContentType getContentType() {
        if (contentType == null) {
            String filename = getFilename();
            return ContentType.forName(filename);
        } else {
            return contentType;
        }
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public InputStream openStream()
            throws IOException {
        return url.openStream();
    }

}
