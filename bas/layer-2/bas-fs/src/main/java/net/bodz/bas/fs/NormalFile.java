package net.bodz.bas.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import net.bodz.bas.collection.iterator.ImmediateIterator;

public class NormalFile
        extends AbstractFile
        implements IFolder {

    private final java.io.File file;

    /**
     * @throws NullPointerException
     *             if <code>file</code> is <code>null</code>
     */
    public NormalFile(IFolder parentFolder, java.io.File file) {
        super(parentFolder, file.getName());
        this.file = file;
    }

    @Override
    protected NormalFile clone() {
        NormalFile o = new NormalFile(getParentFolder(), file);
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

    @Override
    public InputStream newInputStream()
            throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public OutputStream newOutputStream()
            throws IOException {
        boolean appendMode = getAppendMode();
        return new FileOutputStream(file, appendMode);
    }

    @Override
    public RandomAccessFile newRandomAccessFile(String mode)
            throws IOException {
        return new RandomAccessFile(file, mode);
    }

    @Override
    public IFile getFileEntry(String entryName)
            throws IOException {
        File file = new File(this.file, entryName);
        return new NormalFile(this, file);
    }

    @Override
    public IFolder getFolderEntry(String entryName)
            throws IOException {
        File file = new File(this.file, entryName);
        return new NormalFile(this, file);
    }

    @Override
    public ImmediateIterator<IEntry, IOException> entryIterator(IEntryFilter filter) {
        return null;
    }

    @Override
    public ImmediateIterator<IEntry, IOException> entryIterator(IEntryNameFilter filter) {
        return null;
    }

    @Override
    public ImmediateIterator<String, IOException> entryNameIterator(IEntryNameFilter filter) {
        return null;
    }

}
