package net.bodz.bas.vfs;

import java.io.IOException;
import java.nio.file.attribute.*;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.c.java.nio.DeleteOption;
import net.bodz.bas.c.java.nio.DeleteOptions;
import net.bodz.bas.c.primitive.Flags32;
import net.bodz.bas.io.res.IRandomResource;

public abstract class MutableFile
        extends AbstractFile
        implements DosFileAttributeView, PosixFileAttributeView, CommonFileAttributes {

    private MutableFile parentFile;

    private boolean directory;
    private long creationTime = System.currentTimeMillis();
    private long lastModifiedTime = creationTime;
    private long lastAccessTime = creationTime;

    private UserPrincipal owner;
    private GroupPrincipal group;
    private Set<PosixFilePermission> permissions = EnumSet.noneOf(PosixFilePermission.class);

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
    public int delete(DeleteOption... options)
            throws IOException {
        boolean deleteTree = DeleteOptions.isDeleteTree(options);

        int count = 0;

        if (!children.isEmpty()) {
            if (deleteTree)
                for (IFile child : children.values()) {
                    count += child.delete(options);
                }
            else
                return 0;
        }

        MutableFile ancestor = this.parentFile;

        setType(FsObjectType.none);
        detach();
        count++;

        if (DeleteOptions.isRemoveEmptyParents(options))
            while (ancestor.children.isEmpty()) {
                MutableFile pp = ancestor.parentFile;
                ancestor.detach();
                ancestor = pp;
                count++;
            }

        return count;
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
    public CommonFileAttributes readAttributes()
            throws IOException {
        return this;
    }

    @Override
    public String name() {
        return "*";
    }

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
        flags = Flags32.setOrClear(flags, FileFlags.READABLE, readable);
    }

    public void setWritable(boolean writable) {
        flags = Flags32.setOrClear(flags, FileFlags.WRITABLE, writable);
    }

    public void setExecutable(boolean executable) {
        flags = Flags32.setOrClear(flags, FileFlags.EXECUTABLE, executable);
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

    @Override
    public final void setReadOnly(boolean readOnly) {
        setWritable(!readOnly);
    }

    @Override
    public void setHidden(boolean hidden) {
        flags = Flags32.setOrClear(flags, FileFlags.HIDDEN, hidden);
    }

    @Override
    public void setArchive(boolean archive) {
        flags = Flags32.setOrClear(flags, FileFlags.ARCHIVE, archive);
    }

    @Override
    public void setSystem(boolean system) {
        flags = Flags32.setOrClear(flags, FileFlags.SYSTEM, system);
    }

    /** ⇱ Implementation Of {@link PosixFileAttributes}. */
    /* _____________________________ */static section.iface __ATTRS_POSIX__;

    @Override
    public UserPrincipal getOwner()
            throws IOException {
        return owner;
    }

    @Override
    public UserPrincipal owner() {
        return owner;
    }

    @Override
    public GroupPrincipal group() {
        return group;
    }

    @Override
    public Set<PosixFilePermission> permissions() {
        return permissions;
    }

    @Override
    public void setOwner(UserPrincipal owner) {
        this.owner = owner;
    }

    @Override
    public void setGroup(GroupPrincipal group) {
        this.group = group;
    }

    @Override
    public void setPermissions(Set<PosixFilePermission> permissions) {
        if (permissions == null)
            throw new NullPointerException("permissions");
        this.permissions = permissions;
    }

}
