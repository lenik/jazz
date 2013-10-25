package net.bodz.bas.vfs.impl.nio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Collections;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.fn.IFilter;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.io.res.IRandomResource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.PathResource;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.*;

/**
 * @see FileResource
 */
public class NioFile
        extends AbstractFile {

    private final Path _path;

    /**
     * @param _pathstr
     *            The pathname of a {@link java.io.File}.
     * @throws NullPointerException
     *             if <code>jdkPath</code> is <code>null</code>
     */
    NioFile(String _pathstr) {
        this(Paths.get(_pathstr));
    }

    public NioFile(File _file) {
        this(_file.toPath());
    }

    /**
     * @param _path
     *            non-<code>null</code> {@link java.io.File} object.
     */
    public NioFile(Path _path) {
        this(NioVfsDriver.getInstance().getRootDevice(_path), //
                _path.getFileName() == null ? "" : _path.getFileName().toString(), // ""
                _path);
    }

    NioFile(NioVfsDevice rootDevice, String baseName, Path _path) {
        super(rootDevice, baseName);
        this._path = _path;
    }

    public Path getInternalPath() {
        return _path;
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public NioVfsDevice getDevice() {
        return (NioVfsDevice) super.getDevice();
    }

    @Override
    public NioPath getPath() {
        NioVfsDevice device = getDevice();

        String rootName = device.getRootName();
        String _pathstr = _path.toString();

        assert _pathstr.startsWith(rootName);
        String localPath = _pathstr.substring(rootName.length() + 1);

        NioPath path = new NioPath(device.getProtocol(), rootName, localPath);
        return path;
    }

    @Override
    public NioFile getParentFile() {
        Path _parentPath = _path.getParent();
        if (_parentPath == null)
            return null;
        else
            return new NioFile(_parentPath);
    }

    @Override
    public Boolean exists() {
        // LinkOption.NOFOLLOW_LINKS
        return Files.exists(_path);
    }

    @Override
    public int delete(DeleteOption... options)
            throws IOException {
        if (!Files.exists(_path))
            return 0;

        Files.delete(_path);
        return 1;
    }

    @Override
    public boolean deleteOnExit(DeleteOption... options) {
        if (!Files.exists(_path))
            return false;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    Files.delete(_path);
                } catch (IOException e) {
                    // logger.error
                }
            }
        });
        return true;
    }

    /** ⇱ Implementaton Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength()
            throws IOException {
        return Files.size(_path);
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
        File file = _path.toFile();
        if (file != null)
            return FileData.setLength(file, newLength);
        else
            return false;
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        File file = _path.toFile();
        if (file != null)
            return FileData.touch(file, touch);
        else
            return false;
    }

    @Override
    protected IRandomResource _getResource(Charset charset) {
        PathResource resource = new PathResource(_path);
        resource.setCharset(charset);
        return resource;
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public NioFile getChild(String entryName) {
        if (entryName == null)
            throw new NullPointerException("entryName");
        Path childPath = _path.resolve(entryName);
        return new NioFile(childPath);
    }

    static class Path2NioFile
            implements ITransformer<Path, NioFile> {

        @Override
        public NioFile transform(Path input)
                throws RuntimeException {
            return new NioFile(input);
        }

    }

    @Override
    public Iterable<? extends NioFile> children()
            throws VFSException {
        final DirectoryStream<Path> stream;
        try {
            stream = Files.newDirectoryStream(_path);
        } catch (IOException e) {
            // logger.error...
            return Collections.emptyList();
        }
        return Iterables.transform(stream, new Path2NioFile());
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        this.children();
        DirectoryStream.Filter<Path> filter = new Vfs2NioFilenameFilter(nameFilter, this);
        DirectoryStream<Path> stream;
        try {
            stream = Files.newDirectoryStream(_path, filter);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return Iterables.transform(stream, new Path2NioFile());
    }

    @Override
    public Iterable<? extends IFile> children(IFilter<IFile> fileFilter)
            throws VFSException {
        DirectoryStream.Filter<Path> filter = new Vfs2NioFileFilter(fileFilter);
        DirectoryStream<Path> stream;
        try {
            stream = Files.newDirectoryStream(_path, filter);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return Iterables.transform(stream, new Path2NioFile());
    }

    @Override
    public boolean mkdir() {
        try {
            Files.createDirectory(_path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean mkdirs() {
        try {
            Files.createDirectories(_path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /** ⇱ Implementaton Of {@link IFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public boolean isReadable() {
        return Files.isReadable(_path);
    }

    @Override
    public boolean isWritable() {
        return Files.isWritable(_path);
    }

    @Override
    public boolean isExecutable() {
        return Files.isExecutable(_path);
    }

    @Override
    public boolean isRandomAccessible() {
        return true;
    }

    /** ⇱ Implementaton Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    private BasicFileAttributes _bfa;

    private BasicFileAttributes getBasicFileAttributes() {
        if (_bfa == null) {
            try {
                _bfa = Files.readAttributes(_path, BasicFileAttributes.class);
            } catch (IOException e) {
                _bfa = IFileAttributes.NULL;
            }
        }
        return _bfa;
    }

    @Override
    public FileTime lastModifiedTime() {
        return getBasicFileAttributes().lastModifiedTime();
    }

    @Override
    public FileTime lastAccessTime() {
        return getBasicFileAttributes().lastAccessTime();
    }

    @Override
    public FileTime creationTime() {
        return getBasicFileAttributes().creationTime();
    }

    @Override
    public boolean isRegularFile() {
        return getBasicFileAttributes().isRegularFile();
    }

    @Override
    public boolean isDirectory() {
        return getBasicFileAttributes().isDirectory();
    }

    @Override
    public boolean isSymbolicLink() {
        return getBasicFileAttributes().isSymbolicLink();
    }

    @Override
    public boolean isOther() {
        return getBasicFileAttributes().isOther();
    }

    @Override
    public long size() {
        return getBasicFileAttributes().size();
    }

    @Override
    public Object fileKey() {
        return getBasicFileAttributes().fileKey();
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.obj __OBJ__;

    @Override
    public int hashCode() {
        int hash = 0x4c691595;
        hash += _path.hashCode();
        hash += super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NioFile))
            return false;
        NioFile o = (NioFile) obj;
        if (!_path.equals(o._path))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return _path.toString();
    }

}
