package net.bodz.bas.fs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;

import net.bodz.bas.exceptions.ReadOnlyException;

public class URLFile
        extends AbstractFile {

    private final URL url;

    /**
     * @throws NullPointerException
     *             if <code>url</code> is <code>null</code>
     */
    public URLFile(IFolder parentFolder, URL url) {
        super(parentFolder, _getName(url));
        this.url = url;
    }

    @Override
    protected URLFile clone()
            throws CloneNotSupportedException {
        URLFile o = new URLFile(getParentFolder(), url);
        return super.clone(o);
    }

    @Override
    public File getFile() {
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public Path getPath() {
        return getFile().toPath();
    }

    @Override
    public URI getURI() {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public Boolean exists() {
        try {
            InputStream stream = url.openStream();
            stream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Long getCreatedTime() {
        return null;
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
    public void setLastModifiedTime(long date)
            throws IOException {
        throw new ReadOnlyException();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isFolder() {
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
    public boolean delete()
            throws IOException {
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
        }
        // else if (connection instanceof FtpURLConnection) {
        // FtpURLConnection ftp = (FtpURLConnection) connection;
        // }
        throw new UnsupportedOperationException();
    }

    @Override
    public Long getLength() {
        try {
            URLConnection connection = url.openConnection();
            long length = connection.getContentLengthLong();
            if (length == -1)
                return null;
            return length;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public InputStream newInputStream()
            throws IOException {
        return url.openStream();
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        return url.openConnection().getOutputStream();
    }

    @Override
    public RandomAccessFile newRandomAccessFile(String mode)
            throws IOException {
        throw new UnsupportedOperationException();
    }

}
