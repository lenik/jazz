package net.bodz.bas.vfs.impl.nio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Collections;

import net.bodz.bas.c.java.io.FileData;
import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeviceAttributeView;
import net.bodz.bas.c.java.nio.DeviceAttributes;
import net.bodz.bas.c.java.nio.FilePermissionAttributeView;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.io.res.IStreamResource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.io.res.builtin.PathResource;
import net.bodz.bas.t.iterator.Iterables;
import net.bodz.bas.vfs.*;
import net.bodz.bas.vfs.util.Vfs2NioFileFilter;
import net.bodz.bas.vfs.util.Vfs2NioFilenameFilter;

/**
 * @see FileResource
 */
public class NioFile
        extends AbstractFile
        implements IFsDir {

    private final Path _path;
    private NioExtraFileAttributes extraAttrs;

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
    public <V extends FileAttributeView> V getAttributeView(Class<V> type, LinkOption... options) {
        if (type == DeviceAttributeView.class || type == FilePermissionAttributeView.class)
            return type.cast(extraAttrs);
        else
            return Files.getFileAttributeView(_path, type, options);
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options)
            throws IOException {
        BasicFileAttributes attributes = null;

        if (type == DeviceAttributes.class || type == FilePermissionAttributes.class)
            attributes = type.cast(extraAttrs);

        else if (type.equals(BasicFileAttributes.class)) {
            BasicFileAttributeView view = getAttributeView(BasicFileAttributeView.class, options);
            if (view != null)
                attributes = view.readAttributes();
        }

        else if (type.equals(DosFileAttributes.class)) {
            DosFileAttributeView view = getAttributeView(DosFileAttributeView.class, options);
            if (view != null)
                attributes = view.readAttributes();
        }

        else if (type.equals(PosixFileAttributes.class)) {
            PosixFileAttributeView view = getAttributeView(PosixFileAttributeView.class, options);
            if (view != null)
                attributes = view.readAttributes();
        }

        return type.cast(attributes);
    }

    @Override
    public Boolean exists() {
        // LinkOption.NOFOLLOW_LINKS
        return Files.exists(_path);
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
    public boolean delete(DeleteOption... options) {
        if (!Files.exists(_path))
            return false;

        try {
            Files.delete(_path);
            return true;
        } catch (IOException e) {
            return false;
        }
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
    ;

    @Override
    protected IStreamResource newResource(Charset charset) {
        PathResource resource = new PathResource(_path);
        resource.setCharset(charset);
        return resource;
    }

    /** ⇱ Implementaton Of {@link IFsDir}. */
    ;

    @Override
    public NioFile getChild(String entryName) {
        if (entryName == null)
            throw new NullPointerException("entryName");
        Path childPath = _path.resolve(entryName);
        return new NioFile(childPath);
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

    static class Path2NioFile
            implements ITransformer<Path, NioFile> {

        @Override
        public NioFile transform(Path input)
                throws RuntimeException {
            return new NioFile(input);
        }

    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
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
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
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
