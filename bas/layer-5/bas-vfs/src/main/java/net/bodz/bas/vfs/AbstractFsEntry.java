package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;

import net.bodz.bas.traits.Attributes;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractFsEntry
        extends Attributes
        implements IFsEntry {

    private final IVfsDevice device;
    private String baseName;

    public AbstractFsEntry(IVfsDevice device, String baseName) {
        if (device == null)
            throw new NullPointerException("device");
        if (baseName == null)
            throw new NullPointerException("baseName");
        this.device = device;
        this.baseName = baseName;
    }

    protected final AbstractFsEntry nativeClone() {
        try {
            return (AbstractFsEntry) super.clone();
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
    public long getCreationTime() {
        return 0L;
    }

    @Override
    public long getLastModifiedTime() {
        return 0L;
    }

    @Override
    public boolean setLastModifiedTime(long lastModifiedTime) {
        return false;
    }

    @Override
    public int getFlags() {
        return getFlags(FileFlags.MASK_ALL);
    }

    @Override
    public <V extends FileAttributeView> V getAttributeView(Path path, Class<V> type, LinkOption... options) {
        return null;
    }

    @Override
    public <A extends BasicFileAttributes> A getAttributes(Path path, Class<A> type, LinkOption... options)
            throws IOException {
        return null;
    }

    @Override
    public Boolean exists() {
        return null;
    }

    @Override
    public boolean isExisted() {
        return exists() == Boolean.TRUE;
    }

    @Override
    public boolean isNotExisted() {
        return exists() == Boolean.FALSE;
    }

    @Override
    public boolean isBlob() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isReadable() {
        return false;
    }

    @Override
    public boolean setReadable(boolean readable) {
        return false;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public boolean setWritable(boolean writable) {
        return false;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean setHidden(boolean hidden) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean deleteOnExit() {
        return false;
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
