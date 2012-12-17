package net.bodz.bas.vfs.impl.mem;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;
import net.bodz.bas.io.resource.builtin.CharArrayResource;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFileFilter;
import net.bodz.bas.vfs.IFilenameFilter;
import net.bodz.bas.vfs.VFSException;
import net.bodz.bas.vfs.inode.Inode;
import net.bodz.bas.vfs.path.BadPathException;

public class MemoryFile
        extends AbstractFile {

    private MemoryPath path;
    private transient Inode inode;

    public MemoryFile(MemoryVfsDevice device, MemoryPath path) {
        super(device, path.getBaseName());
        this.path = path;
    }

    public MemoryFile(MemoryVfsDevice device, MemoryPath path, Inode inode) {
        this(device, path);
        this.inode = inode;
    }

    @Override
    public MemoryFile clone() {
        return new MemoryFile(getDevice(), path);
    }

    @Override
    public MemoryVfsDevice getDevice() {
        return (MemoryVfsDevice) super.getDevice();
    }

    public synchronized Inode getInode() {
        if (inode == null) {
            Inode n = getDevice().getRootInode();
            for (String entry : path.getLocalEntries()) {
                n = n.getChild(entry);
                if (n == null)
                    return null;
            }
            inode = n;
        }
        return inode;
    }

    @Override
    public IFile getChild(String childName) {
        MemoryPath childPath = (MemoryPath) path.enter().join(childName);
        return new MemoryFile(getDevice(), childPath);
    }

    @Override
    public Iterable<? extends IFile> children(IFilenameFilter nameFilter)
            throws VFSException {
        List<IFile> files = new ArrayList<>();
        for (String childName : getInode().childKeySet())
            if (nameFilter.accept(this, childName)) {
                IFile childFile = getChild(childName);
                files.add(childFile);
            }
        return files;
    }

    @Override
    public Iterable<? extends IFile> children(IFileFilter fileFilter)
            throws VFSException {
        List<IFile> files = new ArrayList<>();
        for (String childName : getInode().childKeySet()) {
            IFile childFile = getChild(childName);
            if (fileFilter.accept(childFile))
                files.add(childFile);
        }
        return files;
    }

    @Override
    public MemoryPath getPath() {
        return path;
    }

    @Override
    protected IStreamResource newResource(Charset charset) {
        Inode inode = getInode();
        if (inode == null)
            return null;

        Serializable data = inode.getData();
        if (data == null)
            return null;

        switch (getInode().getDataType()) {
        case byteArray:
            byte[] byteArray = (byte[]) data;
            return new ByteArrayResource(byteArray);

        case charArray:
            char[] charArray = (char[]) data;
            return new CharArrayResource(charArray);

        default:
            throw new UnexpectedException();
        }
    }

    @Override
    public Long getCreationTime() {
        Inode inode = getInode();
        if (inode == null)
            return null;
        else
            return inode.getCreationTime();
    }

    @Override
    public Long getLastModifiedTime() {
        Inode inode = getInode();
        if (inode == null)
            return null;
        else
            return inode.getLastModifiedTime();
    }

    @Override
    public boolean setLastModifiedTime(long lastModifiedTime) {
        Inode inode = getInode();
        if (inode == null)
            return false;
        inode.setLastModifiedTime(lastModifiedTime);
        return true;
    }

    @Override
    public Long getLength() {
        Inode inode = getInode();
        if (inode == null)
            return 0L; // null

        Serializable data = inode.getData();
        if (data == null)
            return 0L; // null;

        switch (inode.getDataType()) {
        case byteArray:
            byte[] byteArray = (byte[]) data;
            return (long) byteArray.length;

        case charArray:
        default:
            return null;
        }
    }

    @Override
    public Boolean exists() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean isTree() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean isBlob() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        Serializable data = inode.getData();
        return data != null;
    }

    @Override
    public boolean isReadable() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        else
            return inode.isReadable();
    }

    @Override
    public boolean setReadable(boolean readable) {
        Inode inode = getInode();
        if (inode == null)
            return false;
        inode.setReadable(readable);
        return true;
    }

    @Override
    public boolean isWritable() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        else
            return inode.isWritable();
    }

    @Override
    public boolean setWritable(boolean writable) {
        Inode inode = getInode();
        if (inode == null)
            return false;
        inode.setWritable(writable);
        return true;
    }

    @Override
    public boolean isExecutable() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        else
            return inode.isExecutable();
    }

    @Override
    public boolean setExecutable(boolean executable) {
        Inode inode = getInode();
        if (inode == null)
            return false;
        inode.setExecutable(executable);
        return true;
    }

    @Override
    public boolean isHidden() {
        Inode inode = getInode();
        if (inode == null)
            return false;
        else
            return inode.isHidden();
    }

    @Override
    public boolean setHidden(boolean hidden) {
        Inode inode = getInode();
        if (inode == null)
            return false;
        inode.setHidden(hidden);
        return true;
    }

    @Override
    public boolean isIterable() {
        return true;
    }

    @Override
    public boolean setIterable(boolean iterable) {
        return false;
    }

    @Override
    public boolean delete() {
        Inode inode = getInode();
        if (inode != null && inode.isEmpty()) {
            inode.detach();
            inode = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean renameTo(String destLocalPath)
            throws BadPathException {
        if (!isExisted())
            return false;

        MemoryFile destFile = (MemoryFile) getDevice().resolve(destLocalPath);
        if (destFile.isExisted())
            return false;

        String destName = destFile.getName();
        MemoryFile destParentFile = (MemoryFile) destFile.getParentFile();
        if (!destParentFile.createTree())
            return false;

        Inode srcNode = getInode();
        Inode destParentNode = destParentFile.getInode();
        srcNode.detach();
        srcNode.attach(destParentNode, destName);
        return true;
    }

    @Override
    public boolean createTree() {
        Inode inode = getInode();
        if (inode == null) {
            Inode rootInode = getDevice().getRootInode();
            String localPath = path.getLocalPath();
            inode = rootInode.resolve(localPath);
            if (inode == null)
                return false;
        }
        return true;
    }

}
