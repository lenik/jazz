package net.bodz.bas.fs;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.zip.ZipEntry;

public class ZipentryEntry
        extends AbstractEntry {

    private final ZipEntry entry;
    private URL url;

    /**
     * @throws NullPointerException
     *             if <code>entry</code> is <code>null</code>
     */
    public ZipentryEntry(IFolder parentFolder, ZipEntry entry) {
        super(parentFolder, entry.getName());
        this.entry = entry;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public Path getPath() {
        return null;
    }

    @Override
    public URI getURI() {
        try {
            return getURL().toURI();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override
    public URL getURL() {
        if (url == null)
            try {
                url = new URL(getParentFolder().getURL(), entry.getName());
            } catch (MalformedURLException e) {
            }
        return url;
    }

    @Override
    public Boolean exists() {
        return null; // XXX
    }

    @Override
    public Long getCreatedTime() {
        return entry.getTime();
    }

    @Override
    public Long getLastModifiedTime() {
        return entry.getTime();
    }

    @Override
    public void setLastModifiedTime(long date)
            throws IOException {
        entry.setTime(date);
    }

    @Override
    public boolean isFile() {
        return !entry.isDirectory();
    }

    @Override
    public boolean isFolder() {
        return entry.isDirectory();
    }
}
