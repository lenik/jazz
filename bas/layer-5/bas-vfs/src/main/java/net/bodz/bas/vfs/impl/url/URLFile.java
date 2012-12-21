package net.bodz.bas.vfs.impl.url;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.util.Collections;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.VFSException;

public class URLFile
        extends AbstractFile {

    private final URLPath path;
    private URLFileAttributes attributes;

    public URLFile(URLVfsDevice device, URLPath path) {
        super(device, path.getBaseName());
        this.path = path;
        this.attributes = new URLFileAttributes(this);
    }

    public static URLFile resolve(URL url) {
        URLPath path = URLPath.parse(url);
        URLVfsDriver driver = URLVfsDriver.getInstance();
        return driver.resolve(path);
    }

    @Override
    public URLVfsDevice getDevice() {
        return (URLVfsDevice) super.getDevice();
    }

    @Override
    public URLPath getPath() {
        return path;
    }

    static String _getName(URL url) {
        String name = url.getFile();
        int slash = name.lastIndexOf('/');
        if (slash != -1)
            name = name.substring(slash + 1);
        return name;
    }

    @Override
    public <V extends FileAttributeView> V getAttributeView(Class<V> type, LinkOption... options) {
        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options)
            throws IOException {
        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public boolean delete() {
        URL url = path.toURL();

        switch (url.getProtocol()) {
        case "file":
            File file = new File(url.getFile());
            return file.delete();

        case "jar":
            // jar/zip is read-only.
            return false;

        case "http":
        case "https":
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

        default:
            return false;
        }
    }

    // -o IFsBlob

    @Override
    public Long getLength() {
        URL url = path.toURL();

        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url, null);
            if (file != null && file.isFile())
                return file.length();
            else
                return null;

            // case "jar":
            // case "zip":
            // ZipFile...
        default:
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
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
        URL url = path.toURL();
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url, null);
            if (file != null)
                return FileData.setLength(file, newLength);

        default:
            return false;
        }
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        URL url = path.toURL();
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url, null);
            if (file != null)
                return FileData.touch(file, touch);

        default:
            return false;
        }
    }

    @Override
    protected IStreamResource newResource(Charset charset) {
        URL url = path.toURL();
        URLResource resource = new URLResource(url);
        resource.setCharset(charset);
        return resource;
    }

    // -o IFsDir

    @Override
    public URLFile getChild(String entryName)
            throws FileResolveException {
        URL url;
        try {
            // XXX entered...?
            url = new URL(path.toURL(), entryName);
        } catch (MalformedURLException e) {
            throw new FileResolveException(e.getMessage(), e);
        }
        URLPath childURL = URLPath.parse(url);
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

    @Override
    public boolean mkdir() {
        URL url = path.toURL();
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url, null);
            if (file != null)
                return file.mkdir();
        default:
            return false;
        }
    }

    @Override
    public boolean mkdirs() {
        URL url = path.toURL();
        switch (url.getProtocol()) {
        case "file":
            File file = FileURL.toFile(url, null);
            if (file != null)
                return file.mkdirs();
        default:
            return false;
        }
    }

}
