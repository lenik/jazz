package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.attribute.FileTime;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.typer.std.Attributes;
import net.bodz.bas.vfs.path.IPath;

public abstract class FsObject
        extends Attributes
        implements IFsObject, IFsObjectAttributes {

    private final IVfsDevice device;
    private String baseName;

    public FsObject(IVfsDevice device, String baseName) {
        if (device == null)
            throw new NullPointerException("device");
        if (baseName == null)
            throw new NullPointerException("baseName");
        this.device = device;
        this.baseName = baseName;
    }

    protected final FsObject nativeClone() {
        try {
            return (FsObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Get the path string of this file.
     * 
     * @return Non-<code>null</code> path string of this file.
     * @see IPath#toString()
     */
    @Override
    public String toString() {
        return getPath().toString();
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJECT__;

    @Override
    public IVfsDevice getDevice() {
        return device;
    }

    @Override
    public String getName() {
        return baseName;
    }

    protected void setName(String baseName) {
        if (baseName == null)
            throw new NullPointerException("baseName");
        this.baseName = baseName;
    }

    @Override
    public int getFlags() {
        return getFlags(FileFlags.MASK_ALL);
    }

    @Override
    public Boolean exists() {
        return null;
    }

    @Override
    public final boolean isExisted() {
        return exists() == Boolean.TRUE;
    }

    @Override
    public final boolean isNotExisted() {
        return exists() == Boolean.FALSE;
    }

    @Override
    public final boolean isBlob() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public IFileAttributes getAttributes() {
        return null;
    }

    @Override
    public boolean delete(DeleteOption... options) {
        return false;
    }

    @Override
    public boolean deleteOnExit(DeleteOption... options) {
        return false;
    }

    @Override
    public boolean linkTo(String target, boolean symbolic)
            throws IOException {
        String localPath = getPath().getLocalPath();
        return getDevice().createLink(localPath, target, symbolic);
    }

    @Override
    public String readSymLink()
            throws IOException {
        String localPath = getPath().getLocalPath();
        String targetSpec = getDevice().readSymbolicLink(localPath);
        return targetSpec;
    }

    /** ⇱ Implementation Of {@link IFsObjectAttributes}. */
    /* _____________________________ */static section.iface __ATTRIBUTES__;

    @Override
    public FileTime getCreationTime() {
        return getLastModifiedTime();
    }

    @Override
    public boolean setLastModifiedTime(FileTime lastModifiedTime)
            throws IOException {
        return false;
    }

    @Override
    public boolean isSymLink() {
        return false;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean setHidden(boolean hidden)
            throws IOException {
        return false;
    }

    @Override
    public boolean isReadable() {
        return false;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

}
