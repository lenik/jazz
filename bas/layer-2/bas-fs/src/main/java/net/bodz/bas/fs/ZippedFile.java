package net.bodz.bas.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;

public class ZippedFile
        extends AbstractFile
        implements IFolder {

    private final java.io.File file;

    /**
     * @throws NullPointerException
     *             if <code>file</code> is <code>null</code>
     */
    public ZippedFile(IFolder parentFolder, java.io.File file) {
        super(parentFolder, file.getName());
        this.file = file;
    }

    @Override
    protected ZippedFile clone() {
        ZippedFile o = new ZippedFile(getParentFolder(), file);
        return super.clone(o);
    }

    @Override
    public java.io.File getFile() {
        return file;
    }

    @Override
    public Path getPath() {
        return file.toPath();
    }

    @Override
    public URI getURI() {
        return file.toURI();
    }

    @Override
    public URL getURL() {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public Boolean exists() {
        return file.exists();
    }

    @Override
    public Long getCreatedTime() {
        return null;
    }

    @Override
    public Long getLastModifiedTime() {
        return file.lastModified();
    }

    @Override
    public void setLastModifiedTime(long date)
            throws IOException {
        file.setLastModified(date);
    }

    @Override
    public boolean isFile() {
        return file.isFile();
    }

    @Override
    public boolean isFolder() {
        return file.isDirectory();
    }

    @Override
    public boolean delete()
            throws IOException {
        return file.delete();
    }

    @Override
    public Long getLength() {
        return file.length();
    }

    class LoadToolkit
            extends AbstractLoadToolkit {

        public LoadToolkit() {
            super(ZippedFile.this);
        }

        @Override
        public InputStream newInputStream()
                throws IOException {
            return new FileInputStream(file);
        }

    }

    class DumpToolkit
            extends AbstractDumpToolkit {

        public DumpToolkit() {
            super(ZippedFile.this);
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            return new FileOutputStream(file, getAppendMode());
        }

    }

    @Override
    public AbstractReadToolkit forRead() {
        return new LoadToolkit();
    }

    @Override
    public AbstractWriteToolkit forWrite() {
        return new DumpToolkit();
    }

    @Override
    public AbstractLoadToolkit forLoad() {
        return new LoadToolkit();
    }

    @Override
    public AbstractDumpToolkit forDump() {
        return new DumpToolkit();
    }

    @Override
    public ZippedFile getEntry(String entryName)
            throws IOException {
        File file = new File(this.file, entryName);
        return new ZippedFile(this, file);
    }

    @Override
    public List<? extends ZippedFile> listEntries()
            throws IOException {
        return IteratorToList.toList(entryIterator(null));
    }

    @Override
    public List<? extends ZippedFile> listEntries(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(entryIterator(entryNameFilter));
    }

    @Override
    public ImmediateIteratorX<? extends ZippedFile, IOException> entryIterator(final IFilter<String> entryNameFilter) {
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

        return new AbstractImmediateIteratorX<ZippedFile, IOException>() {

            int index = 0;

            @Override
            public ZippedFile next()
                    throws IOException {
                if (index >= list.length)
                    return end();
                String childName = list[index++];
                File childFile = new File(getFile(), childName);
                return new ZippedFile(getParentFolder(), childFile);
            }

        };
    }

}
