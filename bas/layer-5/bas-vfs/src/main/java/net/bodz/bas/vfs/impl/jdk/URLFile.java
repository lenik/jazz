package net.bodz.bas.vfs.impl.jdk;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Collections;

import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.util.IFileFilter;
import net.bodz.bas.vfs.util.IFilenameFilter;

public class URLFile
        extends AbstractFile {

    private static URLVfsDriver driver = URLVfsDriver.getInstance();

    private final URLPath path;

    public URLFile(URLVfsDevice device, URLPath path) {
        super(device, path.getBaseName());
        this.path = path;
    }

    public static URLFile resolve(URL url) {
        return new URLPath(url).resolve();
    }

    @Override
    public URLVfsDevice getDevice() {
        return (URLVfsDevice) super.getDevice();
    }

    @Override
    public URLFile clone() {
        return new URLFile(getDevice(), path).populate(this);
    }

    @Override
    protected URLFile populate(Object obj) {
        super.populate(obj);
        return this;
    }

    @Override
    public IPath getPath() {
        return path;
    }

    @Override
    public Long getLastModifiedTime() {
        URL url = path.toURL();
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
        URL url = path.toURL();
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

    // -o IFsBlob

    @Override
    public Long getLength() {
        URL url = path.toURL();
        try {
            URLConnection connection = url.openConnection();

            long length;
            if (SystemProperties.javaVersion7OrAbove)
                length = connection.getContentLengthLong();
            else
                length = connection.getContentLength();

            return length == -1 ? null : length;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public IStreamResource getResource(Charset charset) {
        URL url = path.toURL();
        URLResource resource = new URLResource(url);
        resource.setCharset(charset);
        return resource;
    }

    // -o IFsTree

    @Override
    public URLFile getChild(String entryName)
            throws FileResolveException {
        URL url;
        try {
            url = new URL(path.toURL(), entryName);
        } catch (MalformedURLException e) {
            throw new FileResolveException(e.getMessage(), e);
        }
        URLPath childURL = new URLPath(url);
        return childURL.resolve();
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        // TODO URL maybe opened like directory stream.
        return Collections.emptyList();
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter nameFilter)
            throws VFSException {
        // TODO URL maybe opened like directory stream.
        return Collections.emptyList();
    }

}
