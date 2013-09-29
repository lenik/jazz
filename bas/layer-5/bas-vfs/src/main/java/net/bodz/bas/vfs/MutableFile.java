package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;
import net.bodz.bas.c.primitive.Flags32;
import net.bodz.bas.io.res.IRandomResource;

public abstract class MutableFile
        extends AbstractFile
        implements DosFileAttributes {

    private MutableFile parentFile;

    private boolean directory;
    private long creationTime = System.currentTimeMillis();
    private long lastModifiedTime = creationTime;
    private long lastAccessTime = creationTime;

    private FsObjectType type = FsObjectType.none;
    private String symbolicTarget;
    private Map<String, IFile> children = new LinkedHashMap<String, IFile>();

    public MutableFile(IVfsDevice device, String baseName, MutableFile parentFile) {
        super(device, baseName);
        this.parentFile = parentFile;
    }

    public FsObjectType getType() {
        return type;
    }

    public void setType(FsObjectType type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public void setName(String baseName) {
        super.setName(baseName);
    }

    /** ⇱ Implementation Of {@link IFsObject}. */
    /* _____________________________ */static section.iface __FS_OBJ__;

    @Override
    public Boolean exists() {
        switch (type) {
        case none:
            return false;
        case symbolicLink:
            // XXX follow...
            return true;
        default:
            return true;
        }
    }

    @Override
    public MutableFile getParentFile() {
        return parentFile;
    }

    public synchronized MutableFile attach(MutableFile newParentFile) {
        if (newParentFile != parentFile) {
            String name = getName();
            detach();
            newParentFile.children.put(name, this);
            parentFile = newParentFile;
        }
        return this;
    }

    public synchronized MutableFile detach() {
        if (parentFile != null) {
            String name = getName();
            parentFile.children.remove(name);
            parentFile = null;
        }
        return this;
    }

    @Override
    public boolean delete(DeleteOption... options) {
        boolean deleteTree = DeleteOptions.isDeleteTree(options);
        boolean removeEmptyParents = DeleteOptions.isRemoveEmptyParents(options);

        if (!children.isEmpty())
            if (!deleteTree)
                return false;

        for (IFile child : children.values())
            if (!child.delete(options))
                return false;

        MutableFile ancestor = this.parentFile;
        // free();
        setType(FsObjectType.none);

        detach();

        if (removeEmptyParents)
            while (ancestor.children.isEmpty()) {
                MutableFile pp = ancestor.parentFile;
                ancestor.detach();
                ancestor = pp;
            }

        return true;
    }

    // protected abstract void free();

    @Override
    public boolean linkTo(String targetSpec, boolean symbolic)
            throws IOException {
        switch (getType()) {
        case none:
        case symbolicLink:
            break;

        case blob:
            // if (! forceMode) return false;
            break;

        default:
            return false;
        }

        setType(FsObjectType.symbolicLink);

        // byte[] encoded = targetSpec.getBytes(Charsets.UTF8);
        symbolicTarget = targetSpec;
        return true;
    }

    public String getSymbolicTarget() {
        return symbolicTarget;
    }

    /** ⇱ Implementation Of {@link IFsDir}. */
    /* _____________________________ */static section.iface __DIR__;

    @Override
    public IFile getChild(String childName) {
        return children.get(childName);
    }

    /** ⇱ Implementation Of {@link IFsBlob}. */
    /* _____________________________ */static section.iface __BLOB__;

    @Override
    public long getLength()
            throws IOException {
        IRandomResource resource = getResource();
        if (resource == null)
            return 0;
        else
            return resource.getLength();
    }

    /** ⇱ Implementation Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_BASIC__;

    @Override
    public final boolean isBlob() {
        return getResource() != null;
    }

    @Override
    public final boolean isRegularFile() {
        return isBlob();
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    @Override
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(lastModifiedTime);
    }

    @Override
    public FileTime lastAccessTime() {
        return FileTime.fromMillis(lastAccessTime);
    }

    @Override
    public FileTime creationTime() {
        return FileTime.fromMillis(creationTime);
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime creationTime)
            throws IOException {
        if (lastModifiedTime != null)
            this.lastModifiedTime = lastModifiedTime.toMillis();
        if (lastAccessTime != null)
            this.lastAccessTime = lastAccessTime.toMillis();
        if (creationTime != null)
            this.creationTime = creationTime.toMillis();
    }

    public void touch() {
        this.lastModifiedTime = System.currentTimeMillis();
        this.lastAccessTime = lastModifiedTime;
    }

    @Override
    public boolean isReadable() {
        return FileFlags.isReadable(flags);
    }

    @Override
    public boolean isWritable() {
        return FileFlags.isWritable(flags);
    }

    @Override
    public boolean isExecutable() {
        return FileFlags.isExecutable(flags);
    }

    public void setReadable(boolean readable) {
        flags = Flags32.setOrClear(flags, readable, FileFlags.READABLE);
    }

    public void setWritable(boolean writable) {
        flags = Flags32.setOrClear(flags, writable, FileFlags.WRITABLE);
    }

    public void setExecutable(boolean executable) {
        flags = Flags32.setOrClear(flags, executable, FileFlags.EXECUTABLE);
    }

    /** ⇱ Implementation Of {@link DosFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_DOS__;

    @Override
    public final boolean isReadOnly() {
        return !isWritable();
    }

    @Override
    public boolean isHidden() {
        return FileFlags.isVisible(flags);
    }

    @Override
    public boolean isArchive() {
        return FileFlags.isArchive(flags);
    }

    @Override
    public boolean isSystem() {
        return FileFlags.isSystem(flags);
    }

    public final void setReadOnly(boolean readOnly) {
        setWritable(!readOnly);
    }

    public void setHidden(boolean hidden) {
        flags = Flags32.setOrClear(flags, hidden, FileFlags.HIDDEN);
    }

    public void setArchive(boolean archive) {
        flags = Flags32.setOrClear(flags, archive, FileFlags.ARCHIVE);
    }

    public void setSystem(boolean system) {
        flags = Flags32.setOrClear(flags, system, FileFlags.SYSTEM);
    }

}
