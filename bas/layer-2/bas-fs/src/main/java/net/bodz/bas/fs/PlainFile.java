package net.bodz.bas.fs;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.io.preparation.AbstractStreamReadPreparation;
import net.bodz.bas.io.preparation.AbstractStreamWritePreparation;
import net.bodz.bas.io.preparation.IStreamReadPreparation;

public class PlainFile
        extends AbstractFile
        implements IFolder {

    private final java.io.File file;

    /**
     * @throws NullPointerException
     *             if <code>file</code> is <code>null</code>
     */
    public PlainFile(java.io.File file) {
        super(file.getName());
        this.file = file;
    }

    @Override
    protected PlainFile clone() {
        PlainFile o = new PlainFile(file);
        return super.clone(o);
    }

    @Override
    public IFolder getParentFolder() {
        File parentFile = file.getParentFile();
        if (parentFile == null)
            return null;
        return new PlainFile(parentFile);
    }

    @Override
    public java.io.File getFile() {
        return file;
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
    public boolean isDeletable() {
        // File parent = file.getParentFile();
        // if (parent != null)
        // return parent.canWrite();
        return file.exists();
    }

    @Override
    public boolean isExecutable() {
        return file.canExecute();
    }

    @Override
    public boolean isIterable() {
        return file.canExecute();
    }

    @Override
    public boolean isReadable() {
        return file.canRead();
    }

    @Override
    public boolean isWritable() {
        return file.canWrite();
    }

    @Override
    public boolean isHidden() {
        return file.isHidden();
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

    class ReadPreparation
            extends AbstractStreamReadPreparation {

        public ReadPreparation() {
            super(PlainFile.this);
        }

        @Override
        public InputStream newInputStream()
                throws IOException {
            return new FileInputStream(file);
        }

    }

    class WritePreparation
            extends AbstractStreamWritePreparation {

        public WritePreparation() {
            super(PlainFile.this);
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            return new FileOutputStream(file, getAppendMode());
        }

    }

    @Override
    public IStreamReadPreparation forRead() {
        return new ReadPreparation();
    }

    @Override
    public AbstractStreamWritePreparation forWrite() {
        return new WritePreparation();
    }

    @Override
    public PlainFile getEntry(String entryName)
            throws IOException {
        File file = new File(this.file, entryName);
        return new PlainFile(file);
    }

    public List<? extends IFsEntry> listEntries(FileFilter fileFilter)
            throws IOException {
        return IteratorToList.toList(entryIterator(fileFilter));
    }

    public List<? extends IFsEntry> listEntries(FilenameFilter fileNameFilter)
            throws IOException {
        return IteratorToList.toList(entryIterator(fileNameFilter));
    }

    public ImmediateIteratorX<? extends IFsEntry, IOException> entryIterator(FileFilter fileFilter) {
        if (fileFilter == null)
            throw new NullPointerException("fileFilter");
        final File[] list = file.listFiles(fileFilter);
        return iteratorFiles(list);
    }

    public ImmediateIteratorX<? extends IFsEntry, IOException> entryIterator(FilenameFilter fileNameFilter) {
        if (fileNameFilter == null)
            throw new NullPointerException("fileNameFilter");
        File[] list = file.listFiles(fileNameFilter);
        return iteratorFiles(list);
    }

    ImmediateIteratorX<? extends IFsEntry, IOException> iteratorFiles(final File[] list) {
        return new AbstractImmediateIteratorX<PlainFile, IOException>() {

            int index = 0;

            @Override
            public PlainFile next()
                    throws IOException {
                if (index >= list.length)
                    return end();
                File childFile = list[index++];
                return new PlainFile(childFile);
            }

        };
    }

    @Override
    public List<? extends PlainFile> listEntries()
            throws IOException {
        return IteratorToList.toList(entryIterator((IFilter<String>) null));
    }

    @Override
    public List<? extends PlainFile> listEntries(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(entryIterator(entryNameFilter));
    }

    @Override
    public ImmediateIteratorX<? extends PlainFile, IOException> entryIterator(final IFilter<String> entryNameFilter) {
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

        return new AbstractImmediateIteratorX<PlainFile, IOException>() {

            int index = 0;

            @Override
            public PlainFile next()
                    throws IOException {
                if (index >= list.length)
                    return end();
                String childName = list[index++];
                File childFile = new File(getFile(), childName);
                return new PlainFile(childFile);
            }

        };
    }

    @Override
    public int hashCode() {
        int hash = 0x4c691595;
        hash += file.hashCode();
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlainFile))
            return false;
        PlainFile o = (PlainFile) obj;
        if (!file.equals(o.file))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return file.toString();
    }

}
