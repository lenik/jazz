package net.bodz.bas.vfs;

import net.bodz.bas.traits.Attributes;
import net.bodz.bas.traits.EmptyAttributes;
import net.bodz.bas.traits.IAttributes;
import net.bodz.bas.vfs.path.IPath;

public abstract class AbstractFsEntry
        extends Attributes
        implements IFsEntry {

    private final IVfsDevice device;
    private String baseName;

    private boolean autoCreateParents;

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
    public abstract IFsEntry clone();

    /**
     * Populate this object with states recognizable from given object.
     * 
     * @return this
     */
    protected AbstractFsEntry populate(Object obj) {
        if (obj instanceof AbstractFsEntry) {
            AbstractFsEntry o = (AbstractFsEntry) obj;
            this.autoCreateParents = o.autoCreateParents;
        }
        return this;
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
    public IAttributes getAttributes() {
        return EmptyAttributes.getInstance();
    }

    @Override
    public Long getCreationTime() {
        return null;
    }

    @Override
    public Long getLastModifiedTime() {
        return null;
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

    @Override
    public boolean isAutoCreateParents() {
        return autoCreateParents;
    }

    @Override
    public void autoCreateParents() {
        autoCreateParents = true;
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
