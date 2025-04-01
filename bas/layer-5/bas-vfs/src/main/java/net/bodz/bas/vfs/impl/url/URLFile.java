package net.bodz.bas.vfs.impl.url;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Collections;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.fn.IFilter;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.FileResolveOptions;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileAttributes;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.IFsBlob;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.PathUnsupportedException;
import net.bodz.bas.vfs.VFSException;

public class URLFile
        extends AbstractFile {

    private final URLPath path;
    private final URL url;

    public URLFile(URLVfsDevice device, URLPath path) {
        super(device, path.getBaseName());
        this.path = path;
        this.url = path.toURL();
    }

    public static URLFile resolve(URL url) {
        return resolve(url, FileResolveOptions.DEFAULT);
    }

    public static URLFile resolve(URL url, FileResolveOptions options) {
        URLPath path = URLPath.parse(url);
        URLVfsDriver driver = URLVfsDriver.getInstance();
        return driver.resolve(path, options);
    }

    @Override
    public URLVfsDevice getDevice() {
        return (URLVfsDevice) super.getDevice();
    }

    @Override
    public URLPath getPath() {
        return path;
    }

    @NotNull
    @Override
    public Path toPath()
            throws PathUnsupportedException {
        try {
            URI uri = path.toURI();
            return Paths.get(uri);
        } catch (URISyntaxException e) {
            throw new PathUnsupportedException(e);
        }
    }

    static String _getName(URL url) {
        String name = url.getFile();
        int slash = name.lastIndexOf('/');
        if (slash != -1)
            name = name.substring(slash + 1);
        return name;
    }

    @Override
    public Boolean exists() {
        return null;
    }

    @Override
    public int delete(DeleteOption... options)
            throws IOException {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                if (file == null)
                    return 0;
                else
                    return FileTree.delete(file, options);

            case "jar":
                // jar/zip is read-only.
                return 0;

            case "http":
            case "https":
                URLConnection connection = url.openConnection();

                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection http = (HttpURLConnection) connection;
                    http.setRequestMethod("DELETE");
                    int responseCode = http.getResponseCode();
                    if (responseCode < 300)
                        return 1;
                    if (responseCode == 404) // Not found
                        return 0;
                    // http.getErrorStream();
                    throw new IOException(http.getResponseMessage());
                } // HTTP

                else
                    return 0;

            default:
                return 0;
        }
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength()
            throws IOException {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                if (file == null)
                    return -1L;
                else
                    return file.length();

                // case "jar":
                // case "zip":
                // ZipFile...
            default:
                URLConnection connection = url.openConnection();

                long length;
                if (SystemProperties.javaVersion7OrAbove)
                    length = connection.getContentLengthLong();
                else
                    length = connection.getContentLength();

                return length;
        }
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
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
    public IRandomResource getResource(Charset charset) {
        throw new UnsupportedOperationException("URL resource is stream-based.");
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        URLResource resource = ResFn.url(url);
        resource.setCharset(charset);
        return resource;
    }

    @Override
    public IStreamOutputTarget getOutputTarget(Charset charset) {
        URLResource resource = ResFn.url(url);
        resource.setCharset(charset);
        return resource;
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public URLFile getChild(String entryName)
            throws FileResolveException {
        URL childUrl;
        try {
            // XXX entered...?
            childUrl = new URL(path.toURL(), entryName);
        } catch (MalformedURLException e) {
            throw new FileResolveException(e.getMessage(), e);
        }
        URLPath childUrlPath = URLPath.parse(childUrl);
        URLFile childFile = (URLFile) childUrlPath.toFile();
        return childFile;
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        // TODO URL maybe opened like directory stream.
        return Collections.emptyList();
    }

    @Override
    public Iterable<? extends IFile> children(IFilter<IFile> nameFilter)
            throws VFSException {
        // TODO URL maybe opened like directory stream.
        return Collections.emptyList();
    }

    @Override
    public boolean mkdir() {
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
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                if (file != null)
                    return file.mkdirs();
            default:
                return false;
        }
    }

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public boolean isReadable() {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                return file == null ? false : file.canRead();
            default:
                return true;
        }
    }

    @Override
    public boolean isWritable() {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                return file == null ? false : file.canWrite();
            default:
                return false;
        }
    }

    @Override
    public boolean isExecutable() {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                return file == null ? false : file.canExecute();
            default:
                return false;
        }
    }

    @Override
    public boolean isRandomAccessible() {
        switch (url.getProtocol()) {
            case "file":
            case "jar":
                return true;
            default:
                return false;
        }
    }

    /** ⇱ Implementaton Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    @Override
    public FileTime lastModifiedTime() {
        long lastModified = 0L;
        try {
            lastModified = url.openConnection().getLastModified();
        } catch (IOException e) {
        }
        return FileTime.fromMillis(lastModified);
    }

    @Override
    public boolean isRegularFile() {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                return file == null ? false : file.isFile();

            default:
                return true;
        }
    }

    @Override
    public boolean isDirectory() {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                return file == null ? false : file.isDirectory();

            default:
                return false;
        }
    }

    @Override
    public Object fileKey() {
        return url.toString();
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        switch (url.getProtocol()) {
            case "file":
                File file = FileURL.toFile(url, null);
                if (file != null)
                    file.setLastModified(lastModifiedTime.toMillis());
                break;
        }
    }

}
