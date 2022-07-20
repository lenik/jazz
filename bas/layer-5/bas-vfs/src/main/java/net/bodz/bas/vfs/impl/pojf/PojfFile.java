package net.bodz.bas.vfs.impl.pojf;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.function.Function;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.io.FileTree;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.fn.IFilter;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.*;

/**
 * @see FileResource
 */
public class PojfFile
        extends AbstractFile {

    private final java.io.File _file;

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
    }

    public java.io.File getInternalFile() {
        return _file;
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

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
        if (_file.isDirectory())
            path = (PojfPath) path.enter();
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
    public int delete(DeleteOption... options) {
        return FileTree.delete(_file, options);
    }

    @Override
    public boolean deleteOnExit(DeleteOption... options) {
        _file.deleteOnExit();
        return true;
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength() {
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
    protected IRandomResource _getResource(Charset charset) {
        FileResource resource = new FileResource(_file);
        resource.setCharset(charset);
        return resource;
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public PojfFile getChild(String childName) {
        File file = new File(this._file, childName);
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
    public Iterable<? extends IFile> children(IFilter<IFile> fileFilter)
            throws VFSException {
        FileFilter adapter = new Vfs2PojfFileFilter(fileFilter);
        File[] childFiles = _file.listFiles(adapter);
        return convertFiles(Arrays.asList(childFiles));
    }

    Iterable<? extends IFile> convertNames(Iterable<String> names) {
        return Iterables.transform(names, new Function<String, PojfFile>() {
            @Override
            public PojfFile apply(String input) {
                return PojfFile.this.getChild(input);
            }
        });
    }

    Iterable<? extends IFile> convertFiles(Iterable<File> files) {
        return Iterables.transform(files, new Function<File, PojfFile>() {
            @Override
            public PojfFile apply(File input) {
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

    /** ⇱ Implementation Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public boolean isReadable() {
        return _file.canRead();
    }

    @Override
    public boolean isWritable() {
        return _file.canWrite();
    }

    @Override
    public boolean isExecutable() {
        return _file.canExecute();
    }

    @Override
    public boolean isRandomAccessible() {
        return true;
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        _file.setLastModified(lastModifiedTime.toMillis());
    }

    /** ⇱ Implementaton Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    @Override
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(_file.lastModified());
    }

    @Override
    public boolean isRegularFile() {
        return _file.isFile();
    }

    @Override
    public boolean isDirectory() {
        return _file.isDirectory();
    }

    @Override
    public boolean isSymbolicLink() {
        Path _path = _file.toPath();
        return Files.isSymbolicLink(_path);
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public long size() {
        return getLength();
    }

    @Override
    public Object fileKey() {
        Path path = _file.toPath();
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        if (view == null)
            return null;
        try {
            return view.readAttributes().fileKey();
        } catch (IOException e) {
            return null;
        }
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

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
