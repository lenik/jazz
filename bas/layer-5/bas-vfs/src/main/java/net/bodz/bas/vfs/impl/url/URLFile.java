package net.bodz.bas.vfs.impl.url;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFileSystem;

public class URLFile
        extends AbstractFile.TransientPath {

    private final URL url;

    public URLFile(URL url) {
        super(url.getPath());
        this.url = url;
    }

    @Override
    public IFileSystem getFileSystem() {
        return URLFileSystem.getInstance();
    }

    @Override
    public URLFile clone() {
        return new URLFile(url).populate(this);
    }

    @Override
    protected URLFile populate(Object obj) {
        super.populate(obj);
        return this;
    }

    @Override
    public Long getLastModifiedTime() {
        try {
            return url.openConnection().getLastModified();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean isBlob() {
        return true;
    }

    @Override
    public boolean isTree() {
        return true;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    static String _getName(URL url) {
        String name = url.getFile();
        int slash = name.lastIndexOf('/');
        if (slash != -1)
            name = name.substring(slash + 1);
        return name;
    }

    @Override
    public boolean delete() {
        try {
            URLConnection connection = url.openConnection();

            if (connection instanceof HttpURLConnection) {
                HttpURLConnection http = (HttpURLConnection) connection;
                http.setRequestMethod("DELETE");
                int responseCode = http.getResponseCode();
                if (responseCode < 300)
                    return true;
                if (responseCode == 404) // Not found
                    return false;
                // http.getErrorStream();
                throw new IOException(http.getResponseMessage());
            } // HTTP

            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getLength() {
        try {
            URLConnection connection = url.openConnection();

            // TODO - connection.getContentLengthLong();
            long length = connection.getContentLength();

            return length == -1 ? null : length;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public URLFile getChild(String entryName)
            throws FileResolveException {
        URL url;
        try {
            url = new URL(this.url, entryName);
        } catch (MalformedURLException e) {
            throw new FileResolveException(e.getMessage(), e);
        }
        return new URLFile(url);
    }

    @Override
    public IStreamResource getResource(Charset charset) {
        URLResource resource = new URLResource(url);
        resource.setCharset(charset);
        return resource;
    }

}
