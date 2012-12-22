package net.bodz.bas.vfs.impl.mem;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;
import net.bodz.bas.c.java.nio.LinkOptions;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.t.buffer.IMovableBuffer;
import net.bodz.bas.t.buffer.MovableByteBuffer;
import net.bodz.bas.t.buffer.MovableCharBuffer;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.inode.Inode;
import net.bodz.bas.vfs.inode.InodeType;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.IPath;

public class MemoryFile
        extends AbstractFile {

    public static final int MOVABLE_BYTE_BUFFER = 1;
    public static final int MOVABLE_CHAR_BUFFER = 2;

    private MemoryPath path;
    private transient Inode inode;
    private MemoryFileAttributes attributes;

    private boolean charOriented;

    public MemoryFile(MemoryVfsDevice device, MemoryPath path) {
        super(device, path.getBaseName());
        this.path = path;
        this.attributes = new MemoryFileAttributes(this);
    }

    public MemoryFile(MemoryVfsDevice device, MemoryPath path, Inode inode) {
        this(device, path);
        this.inode = inode;
    }

    @Override
    public MemoryVfsDevice getDevice() {
        return (MemoryVfsDevice) super.getDevice();
    }

    @Override
    public MemoryPath getPath() {
        return path;
    }

    public synchronized Inode _get(boolean followLinks) {
        if (inode == null) {
            inode = getDevice()._find(path.getLocalPath());
        }

        if (!followLinks)
            return inode;

        Pair<IPath, Inode> pair = getDevice()._follow(path.getLocalPath());
        if (pair == null)
            return null;
        else
            return pair.getValue();
    }

    public synchronized Inode _create() {
        Inode inode = _get(false);
        if (inode == null) {
            inode = getDevice()._resolve(path.getLocalPath());
        }
        return inode;
    }

    @Override
    public <V extends FileAttributeView> V getAttributeView(Class<V> type, LinkOption... options) {
        if (LinkOptions.isFollowLinks(options) && isSymLink()) {
            Pair<IPath, Inode> target = getDevice()._follow(path.getLocalPath());
            if (target == null)
                throw new BadPathException(path.toString());

            MemoryPath targetPath = (MemoryPath) target.getFirst();
            Inode targetInode = target.getSecond();
            MemoryFile targetFile = new MemoryFile(getDevice(), targetPath, targetInode);

            return targetFile.getAttributeView(type);
        }

        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options) {
        if (LinkOptions.isFollowLinks(options) && isSymLink()) {
            Pair<IPath, Inode> target = getDevice()._follow(path.getLocalPath());
            if (target == null)
                throw new BadPathException(path.toString());

            MemoryPath targetPath = (MemoryPath) target.getFirst();
            Inode targetInode = target.getSecond();
            MemoryFile targetFile = new MemoryFile(getDevice(), targetPath, targetInode);

            return targetFile.readAttributes(type);
        }

        if (type.isInstance(attributes))
            return type.cast(attributes);
        else
            return null;
    }

    @Override
    public IFile getChild(String childName) {
        MemoryPath childPath = (MemoryPath) path.enter().join(childName);
        return new MemoryFile(getDevice(), childPath);
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        Inode inode = _get(true);
        if (inode == null)
            return null;

        List<IFile> files = new ArrayList<>();
        for (String childName : inode.childKeySet())
            if (nameFilter.accept(this, childName)) {
                IFile childFile = getChild(childName);
                files.add(childFile);
            }
        return files;
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException {
        Inode inode = _get(true);
        if (inode == null)
            return null;

        List<IFile> files = new ArrayList<>();
        for (String childName : inode.childKeySet()) {
            IFile childFile = getChild(childName);
            if (fileFilter.accept(childFile))
                files.add(childFile);
        }
        return files;
    }

    @Override
    public boolean setLength(long newLength)
            throws IOException {
        if (!mkblob(true))
            return false;

        if (newLength > IMovableBuffer.SIZE_MAX)
            throw new OutOfMemoryError();
        int newSize = (int) newLength;

        Inode inode = _get(false);

        boolean followSymLink = false;
        if (followSymLink)
            while (inode.getType() == InodeType.symbolicLink) {
                String targetSpec = (String) inode.getData();
                inode = inode.resolve(targetSpec);
                if (inode == null)
                    throw new IllegalUsageException();
            }

        switch (inode.getType()) {
        case blob:
        case mixed:
            IMovableBuffer data = (IMovableBuffer) inode.getData();
            data.resize(newSize);
            return true;

        case none:
        case directory:
            return false;

        default:
            return false;
        }
    }

    public boolean isCharOriented() {
        return charOriented;
    }

    public void setCharOriented(boolean charOriented) {
        this.charOriented = charOriented;
    }

    @Override
    public boolean mkblob(boolean touch)
            throws IOException {
        Inode inode = _create();
        if (inode == null)
            throw new IOException("Failed to create inode.");

        inode.setType(InodeType.blob);

        if (isCharOriented()) {
            MovableCharBuffer buffer = new MovableCharBuffer();
            inode.setData(MOVABLE_CHAR_BUFFER, buffer);
        } else {
            MovableByteBuffer buffer = new MovableByteBuffer();
            inode.setData(MOVABLE_BYTE_BUFFER, buffer);
        }

        if (touch)
            inode.touch();

        return true;
    }

    @Override
    public Boolean exists() {
        Inode inode = _get(true);
        if (inode == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean isIterable() {
        Inode inode = _get(true);
        if (inode == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean delete(DeleteOption... options) {
        Inode inode = _get(false);
        if (inode != null && inode.isEmpty()) {
            inode.detach();
            inode = null;

            if (DeleteOptions.isRemoveEmptyParents(options)) {
                // TODO remove empty parents...
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mkdirs() {
        Inode inode = _create();
        if (inode == null)
            return false;

        switch (inode.getType()) {
        case none:
            inode.setType(InodeType.directory);
        case directory:
            return true;

        case mixed:
            return true;

        case symbolicLink:
        case blob:
        default:
            return false;
        }
    }

    @Override
    public boolean mkdir() {
        return mkdirs();
    }

    @Override
    protected IStreamResource newResource(Charset charset) {
        IStreamResource resource = new MemoryResource(this);
        resource.setCharset(charset);
        return resource;
    }

    @Override
    public boolean createLink(String targetSpec, boolean symbolic)
            throws IOException {
        Inode inode = _create();
        if (inode == null)
            throw new IOException("Failed to create inode.");

        if (symbolic) {
            inode.setType(InodeType.symbolicLink);
            inode.setData(targetSpec);
            inode.touch();
        } else {
            IPath targetPath = getPath().join(targetSpec);

            Inode targetInode = getDevice()._find(targetPath.getLocalPath());
            if (targetInode == null)
                throw new IOException("Target isn't existed: " + targetSpec);

            InodeType type = targetInode.getType();
            int dataType = targetInode.getDataType();
            Object data = targetInode.getData();

            inode.setType(type);
            inode.setData(dataType, data);
        }
        return true;
    }

}
