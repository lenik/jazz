package net.bodz.bas.vfs.inode;

import java.io.IOException;
import java.nio.file.attribute.*;
import java.util.Set;

import net.bodz.bas.c.java.nio.DeviceAttributeView;
import net.bodz.bas.c.java.nio.DeviceAttributes;
import net.bodz.bas.c.java.nio.FilePermissionAttributeView;
import net.bodz.bas.c.java.nio.FilePermissionAttributes;
import net.bodz.bas.c.java.nio.UnitModeBits;
import net.bodz.bas.c.object.ObjectInfo;

public abstract class InodeFileAttributes
        implements //
        BasicFileAttributeView, BasicFileAttributes, //
        DosFileAttributeView, DosFileAttributes, //
        PosixFileAttributeView, PosixFileAttributes, //
        FilePermissionAttributeView, FilePermissionAttributes, //
        DeviceAttributeView, DeviceAttributes {

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

    // -o BasicFileAttributes and View

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

    // -o DosFileAttributes and View

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

    // -o PosixFileAttributes and View

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
        return UnitModeBits.toPermissions(mode);
    }

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

    @Override
    public void setGroup(GroupPrincipal group)
            throws IOException {
        getInode().setGroup(group);
    }

    @Override
    public void setPermissions(Set<PosixFilePermission> permissions)
            throws IOException {
        int mode = UnitModeBits.fromPermissions(permissions);
        getInode().setMode(mode);
    }

    // -o FilePermissionAttributes and View

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

    @Override
    public boolean isDeletable() {
        Inode parent = getInode().getParent();
        if (parent == null)
            return false;
        else
            return parent.isWritable();
    }

    // -o DeviceAttributes

    @Override
    public boolean isRandomAccessible() {
        return true;
    }

    @Override
    public boolean isBlockAccessible() {
        return true;
    }

}
