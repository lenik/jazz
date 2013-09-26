package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;
import net.bodz.bas.typer.std.Attributes;
import net.bodz.bas.vfs.path.IPath;

public abstract class FsObject
        extends Attributes
        implements IFsObject {

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
    public final FileTime getCreationTime() {
        try {
            BasicFileAttributes attrs = this.readAttributes(BasicFileAttributes.class);
            return attrs == null ? null : attrs.creationTime();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public final FileTime getLastModifiedTime() {
        try {
            BasicFileAttributes attrs = this.readAttributes(BasicFileAttributes.class);
            return attrs == null ? null : attrs.lastModifiedTime();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public final boolean setLastModifiedTime(FileTime lastModifiedTime)
            throws IOException {
        BasicFileAttributeView view = this.getAttributeView(BasicFileAttributeView.class);
        if (view == null)
            return false;

        BasicFileAttributes attrs = view.readAttributes();
        FileTime creationTime = attrs.creationTime();
        FileTime lastAccessTime = attrs.lastAccessTime();

        view.setTimes(lastModifiedTime, lastAccessTime, creationTime);
        return true;
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
        try {
            BasicFileAttributes attrs = this.readAttributes(BasicFileAttributes.class);
            return attrs == null ? false : attrs.isRegularFile();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public final boolean isDirectory() {
        try {
            BasicFileAttributes attrs = this.readAttributes(BasicFileAttributes.class);
            return attrs == null ? false : attrs.isDirectory();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public final boolean isSymLink() {
        try {
            BasicFileAttributes attrs = this.readAttributes(BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            return attrs == null ? false : attrs.isSymbolicLink();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public final boolean isHidden() {
        try {
            DosFileAttributes attrs = this.readAttributes(DosFileAttributes.class);
            return attrs == null ? false : attrs.isHidden();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public final boolean setHidden(boolean hidden)
            throws IOException {
        DosFileAttributeView view = this.getAttributeView(DosFileAttributeView.class);
        if (view == null)
            return false;
        view.setHidden(true);
        return true;
    }

    @Override
    public final boolean isReadable() {
        try {
            FilePermissionAttributes attrs = this.readAttributes(FilePermissionAttributes.class);
            return attrs == null ? false : attrs.isReadable();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public final boolean isWritable() {
        try {
            FilePermissionAttributes attrs = this.readAttributes(FilePermissionAttributes.class);
            return attrs == null ? false : attrs.isWritable();
        } catch (IOException e) {
            return false;
        }
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
    public boolean createLink(String targetSpec, boolean symbolic)
            throws IOException {
        String localPath = getPath().getLocalPath();
        return getDevice().createLink(localPath, targetSpec, symbolic);
    }

    @Override
    public String readSymLink()
            throws IOException {
        String localPath = getPath().getLocalPath();
        String targetSpec = getDevice().readSymbolicLink(localPath);
        return targetSpec;
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

}
