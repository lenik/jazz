package net.bodz.bas.vfs.impl.javafile;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import net.bodz.bas.closure.IFilter;
import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFolder;
import net.bodz.bas.vfs.IFsEntry;

public class FileFile
        extends AbstractFile
        implements IFolder {

    private final java.io.File file;

    /**
     * @throws NullPointerException
     *             if <code>file</code> is <code>null</code>
     */
    public FileFile(java.io.File file) {
        super(file.getName());
        this.file = file;
    }

    @Override
    protected FileFile clone() {
        FileFile o = new FileFile(file);
        return super.clone(o);
    }

    @Override
    public IFolder getParentFolder() {
        File parentFile = file.getParentFile();
        if (parentFile == null)
            return null;
        return new FileFile(parentFile);
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
    public long getLength() {
        return file.length();
    }
 
    
    @Override
    public IStreamInputSource toSource() {
        return new LocalFileResource(file);
    }
    
    @Override
    public IStreamOutputTarget toTarget() {
        return new LocalFileResource(file);
    }
    
    @Override
    public FileFile getChild(String entryName)
            throws IOException {
        File file = new File(this.file, entryName);
        return new FileFile(file);
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
        return new AbstractImmediateIteratorX<FileFile, IOException>() {

            int index = 0;

            @Override
            public FileFile next()
                    throws IOException {
                if (index >= list.length)
                    return end();
                File childFile = list[index++];
                return new FileFile(childFile);
            }

        };
    }

    @Override
    public List<? extends FileFile> listChildren()
            throws IOException {
        return IteratorToList.toList(childIterator((IFilter<String>) null));
    }

    @Override
    public List<? extends FileFile> listChildren(IFilter<String> entryNameFilter)
            throws IOException {
        return IteratorToList.toList(childIterator(entryNameFilter));
    }

    @Override
    public ImmediateIteratorX<? extends FileFile, IOException> childIterator(final IFilter<String> entryNameFilter) {
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

        return new AbstractImmediateIteratorX<FileFile, IOException>() {

            int index = 0;

            @Override
            public FileFile next()
                    throws IOException {
                if (index >= list.length)
                    return end();
                String childName = list[index++];
                File childFile = new File(getFile(), childName);
                return new FileFile(childFile);
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
        if (!(obj instanceof FileFile))
            return false;
        FileFile o = (FileFile) obj;
        if (!file.equals(o.file))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return file.toString();
    }

}
