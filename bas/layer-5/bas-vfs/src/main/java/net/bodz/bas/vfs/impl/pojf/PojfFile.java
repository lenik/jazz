package net.bodz.bas.vfs.impl.pojf;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.util.Arrays;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.FileResource;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.util.Vfs2PojfFileFilter;
import net.bodz.bas.vfs.util.Vfs2PojfFilenameFilter;

/**
 * @see FileResource
 */
public class PojfFile
        extends AbstractFile
        implements IFsDir {

    private final java.io.File _file;
    private PojfFileAttributes attributes;

    /**
     * @param _pathstr
     *            The pathname of a {@link java.io.File}.
     * @throws NullPointerException
     *             if <code>pojfPath</code> is <code>null</code>
     */
    public PojfFile(String _pathstr) {
        this(new File(_pathstr));
    }

    /**
     * @param _file
     *            non-<code>null</code> {@link java.io.File} object.
     */
    public PojfFile(File _file) {
        this(PojfVfsDriver.getInstance().getDrive(_file), _file);
    }

    PojfFile(PojfVfsDevice driveDevice, File _file) {
        super(driveDevice, _file.getName());
        this._file = _file;
        this.attributes = new PojfFileAttributes(_file);
    }

    public java.io.File getInternalFile() {
        return _file;
    }

    @Override
    public PojfVfsDevice getDevice() {
        return (PojfVfsDevice) super.getDevice();
    }

    @Override
    public PojfPath getPath() {
        PojfVfsDevice device = getDevice();

        String driveName = device.getDriveName();
        String origPath = _file.getPath();

        String localPath;
        if (driveName == null)
            localPath = origPath;
        else {
            assert origPath.startsWith(driveName);
            localPath = origPath.substring(driveName.length());
        }

        PojfPath path = new PojfPath(device.getProtocol(), driveName, localPath);
        return path;
    }

    @Override
    public PojfFile getParentFile() {
        File parentFile = _file.getParentFile();
        if (parentFile == null)
            return null;
        else
            return new PojfFile(parentFile);
    }

    @Override
    public Boolean exists() {
        return _file.exists();
    }

    @Override
    public <V extends FileAttributeView> V getAttributeView(Class<V> type, LinkOption... options) {
        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options)
            throws IOException {
        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public Long getLength() {
        return _file.length();
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
        return FileData.setLength(_file, newLength);
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        return FileData.touch(_file, touch);
    }

    @Override
    public boolean delete(DeleteOption... options) {
        return FileTree.delete(_file, options);
    }

    @Override
    public boolean deleteOnExit(DeleteOption... options) {
        _file.deleteOnExit();
        return true;
    }

// -o IFsBlob

    @Override
    protected IStreamResource newResource(Charset charset) {
        FileResource resource = new FileResource(_file);
        resource.setCharset(charset);
        return resource;
    }

    // -o IFsTree

    @Override
    public PojfFile getChild(String entryName) {
        File file = new File(this._file, entryName);
        return new PojfFile(file);
    }

    @Override
    public Iterable<? extends IFile> children()
            throws VFSException {
        String[] childNames = _file.list();
        return convertNames(Arrays.asList(childNames));
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        FilenameFilter adapter = new Vfs2PojfFilenameFilter(nameFilter, this);
        String[] childNames = _file.list(adapter);
        return convertNames(Arrays.asList(childNames));
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException {
        FileFilter adapter = new Vfs2PojfFileFilter(fileFilter);
        File[] childFiles = _file.listFiles(adapter);
        return convertFiles(Arrays.asList(childFiles));
    }

    Iterable<? extends IFile> convertNames(Iterable<String> names) {
        return Iterables.transform(names, new ITransformer<String, PojfFile>() {
            @Override
            public PojfFile transform(String input)
                    throws RuntimeException {
                return PojfFile.this.getChild(input);
            }
        });
    }

    Iterable<? extends IFile> convertFiles(Iterable<File> files) {
        return Iterables.transform(files, new ITransformer<File, PojfFile>() {
            @Override
            public PojfFile transform(File input)
                    throws RuntimeException {
                return new PojfFile(input);
            }
        });
    }

    @Override
    public boolean mkdir() {
        return _file.mkdir();
    }

    @Override
    public boolean mkdirs() {
        return _file.mkdirs();
    }

    @Override
    public int hashCode() {
        int hash = 0x4c691595;
        hash += _file.hashCode();
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PojfFile))
            return false;
        PojfFile o = (PojfFile) obj;
        if (!_file.equals(o._file))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return _file.toString();
    }

}
