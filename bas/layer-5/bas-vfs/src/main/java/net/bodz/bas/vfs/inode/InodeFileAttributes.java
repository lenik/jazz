package net.bodz.bas.vfs.inode;

import java.io.IOException;
import java.nio.file.attribute.*;
import java.util.Set;

import net.bodz.bas.c.java.nio.UnixModeBits;
import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.vfs.IFileAttributes;

public abstract class InodeFileAttributes
        implements IFileAttributes, //
        DosFileAttributeView, DosFileAttributes, //
        PosixFileAttributeView, PosixFileAttributes //
{

    protected abstract Inode getInode();

    protected abstract long getDataSize(Object data);

    protected abstract Inode resolveSymlink(String targetSpec);

    @Override
    public String name() {
        return "*";
    }

    @Override
    public InodeFileAttributes readAttributes()
            throws IOException {
        return this;
    }

    /** ⇱ Implementaton Of {@link BasicFileAttributes}. */
    /* _____________________________ */static section.iface __BASIC__;

    @Override
    public FileTime lastModifiedTime() {
        return FileTime.fromMillis(getInode().getLastModifiedTime());
    }

    @Override
    public FileTime lastAccessTime() {
        return FileTime.fromMillis(getInode().getLastAccessTime());
    }

    @Override
    public FileTime creationTime() {
        return FileTime.fromMillis(getInode().getCreationTime());
    }

    @Override
    public boolean isRegularFile() {
        Inode inode = this.getInode();

        while (inode.getType() == InodeType.symbolicLink) {
            String targetSpec = (String) inode.getData();
            inode = resolveSymlink(targetSpec);
            // TODO symlink loop detect here.
            if (inode == null)
                return false;
        }

        switch (inode.getType()) {
        case none:
        case directory:
            return false;

        case blob:
        case mixed:
            return true;

        default:
            return false;
        }
    }

    @Override
    public boolean isDirectory() {
        Inode inode = this.getInode();

        while (inode.getType() == InodeType.symbolicLink) {
            String targetSpec = (String) inode.getData();
            inode = resolveSymlink(targetSpec);
            // TODO symlink loop detect here.
            if (inode == null)
                return false;
        }

        switch (inode.getType()) {
        case directory:
        case mixed:
            return true;

        case none:
        case blob:
            return false;

        default:
            return false;
        }
    }

    @Override
    public boolean isSymbolicLink() {
        return getInode().getType() == InodeType.symbolicLink;
    }

    @Override
    public boolean isOther() {
        switch (getInode().getType()) {
        case none:
        case directory:
        case blob:
        case mixed:
            return false;

        case symbolicLink:
            return false;

        default:
            return true;
        }
    }

    @Override
    public long size() {
        switch (getInode().getType()) {
        case none:
        case directory:
            return 0L;

        case blob:
        case mixed:
            Object data = getInode().getData();
            return getDataSize(data);

        case symbolicLink:
            String targetSpec = (String) getInode().getData();
            return targetSpec.length();

        default:
            return 0L;
        }
    }

    @Override
    public Object fileKey() {
        return ObjectInfo.getSimpleId(getInode());
    }

    @Override
    public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
            throws IOException {
        getInode().setCreationTime(createTime.toMillis());
        getInode().setLastModifiedTime(lastModifiedTime.toMillis());
        getInode().setLastAccessTime(lastAccessTime.toMillis());
    }

    /** ⇱ Implementaton Of {@link DosFileAttributes}. */
    /* _____________________________ */static section.iface __DOS__;

    @Override
    public boolean isReadOnly() {
        return getInode().isReadOnly();
    }

    @Override
    public boolean isHidden() {
        return getInode().isHidden();
    }

    @Override
    public boolean isArchive() {
        return getInode().isArchive();
    }

    @Override
    public boolean isSystem() {
        return getInode().isSystem();
    }

    @Override
    public void setReadOnly(boolean value)
            throws IOException {
        getInode().setReadOnly(value);
    }

    @Override
    public void setHidden(boolean value)
            throws IOException {
        getInode().setHidden(value);
    }

    @Override
    public void setSystem(boolean value)
            throws IOException {
        getInode().setSystem(value);
    }

    @Override
    public void setArchive(boolean value)
            throws IOException {
        getInode().setArchive(value);
    }

    /** ⇱ Implementaton Of {@link PosixFileAttributes}. */
    /* _____________________________ */static section.iface __POSIX__;

    @Override
    public UserPrincipal owner() {
        return getInode().getOwner();
    }

    @Override
    public GroupPrincipal group() {
        return getInode().getGroup();
    }

    @Override
    public Set<PosixFilePermission> permissions() {
        int mode = getInode().getMode();
        return UnixModeBits.toPermissions(mode);
    }

    /** ⇱ Implementaton Of {@link PosixFileAttributeView}. */
    /* _____________________________ */static section.iface __POSIX_VIEW__;

    @Override
    public void setGroup(GroupPrincipal group)
            throws IOException {
        getInode().setGroup(group);
    }

    @Override
    public void setPermissions(Set<PosixFilePermission> permissions)
            throws IOException {
        int mode = UnixModeBits.fromPermissions(permissions);
        getInode().setMode(mode);
    }

    /** ⇱ Implementaton Of {@link FileOwnerAttributeView}. */
    /* _____________________________ */static section.iface __FILE_OWNER__;

    @Override
    public UserPrincipal getOwner()
            throws IOException {
        return getInode().getOwner();
    }

    @Override
    public void setOwner(UserPrincipal owner)
            throws IOException {
        getInode().setOwner(owner);
    }

    /** ⇱ Implementaton Of {@link FilePermissionAttributes}. */
    /* _____________________________ */static section.iface __FILE_PERMISSION__;

    @Override
    public boolean isReadable() {
        return getInode().isReadable();
    }

    @Override
    public boolean isWritable() {
        return getInode().isWritable();
    }

    @Override
    public boolean isExecutable() {
        return getInode().isExecutable();
    }

    /** ⇱ Implementaton Of {@link DeviceAttributes}. */
    /* _____________________________ */static section.iface __DEVICE__;

    @Override
    public boolean isRandomAccessible() {
        return true;
    }

}
