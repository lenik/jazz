package net.bodz.bas.fs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.exceptions.ReadOnlyException;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;

public class URLFile
        extends AbstractFile
        implements IFolder {

    private final URL url;

    /**
     * @throws NullPointerException
     *             if <code>url</code> is <code>null</code>
     */
    public URLFile(URL url) {
        super(_getName(url));
        this.url = url;
    }

    @Override
    protected URLFile clone()
            throws CloneNotSupportedException {
        URLFile o = new URLFile(url);
        return super.clone(o);
    }

    @Override
    public IFolder getParentFolder() {

        return null;
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

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return true;
    }

    @Override
    public boolean isIterable() {
        return true;
    }

    @Override
    public boolean isDeletable() {
        return true;
    }

    @Override
    public boolean isExecutable() {
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

    class ReadPreparation
            extends StreamReadPreparation {

        public ReadPreparation() {
            super(URLFile.this);
        }

        @Override
        public InputStream newInputStream()
                throws IOException {
            return url.openStream();
        }

    }

    class WritePreparation
            extends StreamWritePreparation {

        public WritePreparation() {
            super(URLFile.this);
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            return url.openConnection().getOutputStream();
        }

    }

    @Override
    public IStreamReadPreparation forRead() {
        return new ReadPreparation();
    }

    @Override
    public IStreamWritePreparation forWrite() {
        return new WritePreparation();
    }

    @Override
    public URLFile getEntry(String entryName)
            throws IOException {
        URL url = new URL(this.url, entryName);
        return new URLFile(url);
    }

    @Override
    public List<? extends URLFile> listEntries()
            throws IOException {
        return IteratorToList.toList(entryIterator(null));
    }

    @Override
    public List<? extends URLFile> listEntries(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(entryIterator(entryNameFilter));
    }

    @Override
    public ImmediateIteratorX<? extends URLFile, IOException> entryIterator(final IFilter<String> entryNameFilter) {
        File file = getFile();
        if (file.isDirectory()) {
            final String[] list;
            if (entryNameFilter == null)
                list = file.list();
            else
                list = file.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return entryNameFilter.accept(name);
                    }
                });

            return new AbstractImmediateIteratorX<URLFile, IOException>() {

                int index = 0;

                @Override
                public URLFile next()
                        throws IOException {
                    if (index >= list.length)
                        return end();
                    String childName = list[index++];
                    File childFile = new File(getFile(), childName);
                    URL childURL = childFile.toURI().toURL();
                    return new URLFile(childURL);
                }

            };
        }
        throw new UnsupportedOperationException();
    }

}
