package net.bodz.bas.fs;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;

public class ZipFolder
        extends AbstractEntry
        implements IFolder {

    @Override
    public Boolean exists() {
        return null;
    }

    @Override
    public Long getCreatedTime() {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public Long getLastModifiedTime() {
        return null;
    }

    @Override
    public Path getPath() {
        return null;
    }

    @Override
    public URI getURI() {
        return null;
    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public void setLastModifiedTime(long date)
            throws IOException {
    }

    @Override
    public ImmediateIteratorX<? extends IEntry, IOException> entryIterator(IFilter<String> entryNameFilter) {
        return null;
    }

    @Override
    public IEntry getEntry(String entryName)
            throws IOException {
        return null;
    }

    @Override
    public List<? extends IEntry> listEntries()
            throws IOException {
        return null;
    }

    @Override
    public List<? extends IEntry> listEntries(IFilter<String> entryNameFilter)
            throws IOException {
        return null;
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

}
