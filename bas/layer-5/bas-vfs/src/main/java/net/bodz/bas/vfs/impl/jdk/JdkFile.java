package net.bodz.bas.vfs.impl.jdk;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.util.Vfs2JdkFileFilter;
import net.bodz.bas.vfs.util.Vfs2JdkFilenameFilter;

/**
 * @see LocalFileResource
 */
public class JdkFile
        extends AbstractFile
        implements IFsDir {

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
        this(JdkVfsDriver.getInstance().getDrive(jdkFile), jdkFile);
    }

    JdkFile(JdkVfsDevice driveDevice, File jdkFile) {
        super(driveDevice, jdkFile.getName());
        this.origFile = jdkFile;
    }

    public java.io.File getOrigFile() {
        return origFile;
    }

    @Override
    public JdkVfsDevice getDevice() {
        return (JdkVfsDevice) super.getDevice();
    }

    @Override
    public JdkPath getPath() {
        JdkVfsDevice device = getDevice();

        String driveName = device.getDriveName();
        String origPath = origFile.getPath();

        String localPath;
        if (driveName == null)
            localPath = origPath;
        else {
            assert origPath.startsWith(driveName);
            localPath = origPath.substring(driveName.length());
        }

        JdkPath path = new JdkPath(device.getProtocol(), driveName, localPath);
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
    public long getLastModifiedTime() {
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
    public boolean isDirectory() {
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
    public boolean setLength(long newLength)
            throws IOException {
        return FileData.setLength(origFile, newLength);
    }

    @Override
    public boolean touch(boolean updateLastModifiedTime)
            throws IOException {
        return FileData.touch(origFile, updateLastModifiedTime);
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
    public boolean setIterable(boolean iterable) {
        if (origFile.isDirectory())
            return origFile.setExecutable(iterable);
        else
            return false;
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
    protected IStreamResource newResource(Charset charset) {
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
        FilenameFilter adapter = new Vfs2JdkFilenameFilter(nameFilter, this);
        String[] childNames = origFile.list(adapter);
        return convertNames(Arrays.asList(childNames));
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException {
        FileFilter adapter = new Vfs2JdkFileFilter(fileFilter);
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
    public boolean mkdir() {
        return origFile.mkdir();
    }

    @Override
    public boolean mkdirs() {
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
