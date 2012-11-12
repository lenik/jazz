package net.bodz.bas.vfs.impl.jdk;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.util.Arrays;

import net.bodz.bas.c.java.util.Iterables;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.model.ITransformer;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFsTree;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.util.FileFilterAdapter;
import net.bodz.bas.vfs.util.FilenameFilterAdapterWithDir;
import net.bodz.bas.vfs.util.IFileFilter;
import net.bodz.bas.vfs.util.IFilenameFilter;

/**
 * @see LocalFileResource
 */
public class JdkFile
        extends AbstractFile
        implements IFsTree {

    private static final long serialVersionUID = 1L;
    private static final JdkVfsDevice device = JdkVfsDevice.getInstance();

    private final java.io.File origFile;

    /**
     * @param jdkPath
     *            The pathname of a {@link java.io.File}.
     * @throws NullPointerException
     *             if <code>jdkPath</code> is <code>null</code>
     */
    public JdkFile(String jdkPath) {
        this(new File(jdkPath));
    }

    /**
     * @param jdkFile
     *            non-<code>null</code> {@link java.io.File} object.
     */
    public JdkFile(File jdkFile) {
        super(device, jdkFile.getName());
        this.origFile = jdkFile;
    }

    @Override
    public JdkFile clone() {
        JdkFile o = new JdkFile(origFile);
        o.populate(this);
        return o;
    }

    public java.io.File getOrigFile() {
        return origFile;
    }

    @Override
    public JdkVfsDevice getDevice() {
        return device;
    }

    @Override
    public JdkPath getPath() {
        JdkPath path = new JdkPath(device, origFile.getPath());
        return path;
    }

    @Override
    public JdkFile getParentFile() {
        File parentFile = origFile.getParentFile();
        if (parentFile == null)
            return null;
        else
            return new JdkFile(parentFile);
    }

    @Override
    public Boolean exists() {
        return origFile.exists();
    }

    @Override
    public Long getLastModifiedTime() {
        return origFile.lastModified();
    }

    @Override
    public boolean setLastModifiedTime(long date) {
        return origFile.setLastModified(date);
    }

    @Override
    public boolean isBlob() {
        return origFile.isFile();
    }

    @Override
    public boolean isTree() {
        return origFile.isDirectory();
    }

    @Override
    public boolean isReadable() {
        return origFile.canRead();
    }

    @Override
    public boolean isWritable() {
        return origFile.canWrite();
    }

    @Override
    public boolean isHidden() {
        return origFile.isHidden();
    }

    @Override
    public boolean isExecutable() {
        return origFile.canExecute();
    }

    @Override
    public Long getLength() {
        return origFile.length();
    }

    @Override
    public boolean isSeekable() {
        return true;
    }

    @Override
    public boolean isIterable() {
        return origFile.isDirectory() && origFile.canExecute();
    }

    @Override
    public boolean delete() {
        return origFile.delete();
    }

    @Override
    public boolean deleteOnExit() {
        origFile.deleteOnExit();
        return true;
    }

// -o IFsBlob

    @Override
    public IStreamResource getResource(Charset charset) {
        LocalFileResource resource = new LocalFileResource(origFile);
        resource.setCharset(charset);
        return resource;
    }

    // -o IFsTree

    @Override
    public JdkFile getChild(String entryName) {
        File file = new File(this.origFile, entryName);
        return new JdkFile(file);
    }

    @Override
    public Iterable<? extends IFile> children()
            throws VFSException {
        String[] childNames = origFile.list();
        return convertNames(Arrays.asList(childNames));
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        FilenameFilter adapter = new FilenameFilterAdapterWithDir(nameFilter, this);
        String[] childNames = origFile.list(adapter);
        return convertNames(Arrays.asList(childNames));
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException {
        FileFilter adapter = new FileFilterAdapter(fileFilter);
        File[] childFiles = origFile.listFiles(adapter);
        return convertFiles(Arrays.asList(childFiles));
    }

    Iterable<? extends IFile> convertNames(Iterable<String> names) {
        return Iterables.transform(names, new ITransformer<String, JdkFile>() {
            @Override
            public JdkFile transform(String input)
                    throws RuntimeException {
                return JdkFile.this.getChild(input);
            }
        });
    }

    Iterable<? extends IFile> convertFiles(Iterable<File> files) {
        return Iterables.transform(files, new ITransformer<File, JdkFile>() {
            @Override
            public JdkFile transform(File input)
                    throws RuntimeException {
                return new JdkFile(input);
            }
        });
    }

    @Override
    public boolean createTree() {
        return origFile.mkdirs();
    }

    @Override
    public int hashCode() {
        int hash = 0x4c691595;
        hash += origFile.hashCode();
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JdkFile))
            return false;
        JdkFile o = (JdkFile) obj;
        if (!origFile.equals(o.origFile))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return origFile.toString();
    }

}
