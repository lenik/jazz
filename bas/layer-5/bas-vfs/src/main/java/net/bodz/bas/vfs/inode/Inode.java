package net.bodz.bas.vfs.inode;

import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;

import net.bodz.bas.c.java.nio.UnixModeBits;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.tree.AbstractMapTreeNode;

public class Inode
        extends AbstractMapTreeNode<Inode> {

    private static final long serialVersionUID = 1L;

    private InodeType type = InodeType.none;
    private int dataType;
    private Object data;

    // private short uid;
    // private short gid;
    // private int permission;

    // for DOS.
    private boolean archive;
    private boolean hidden;
    private boolean system;

    private long creationTime = System.currentTimeMillis();
    private long lastModifiedTime = creationTime;
    private long lastAccessTime = creationTime;

    UserPrincipal owner;
    GroupPrincipal group;
    int mode;

    public Inode(Inode parent) {
        super(parent);
    }

    @Override
    protected Inode newChild()
            throws CreateException {
        return new Inode(this);
    }

    public InodeType getType() {
        return type;
    }

    public void setType(InodeType type) {
        if (type == null)
            throw new NullPointerException("dataType");
        this.type = type;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setData(int dataType, Object data) {
        setDataType(dataType);
        setData(data);
    }

    public void touch() {
        lastModifiedTime = System.currentTimeMillis();
    }

    public boolean isReadable() {
        return (mode & UnixModeBits.OWNER_READ) != 0;
    }

    public void setReadable(boolean readable) {
        mode |= UnixModeBits.OWNER_READ;
    }

    public boolean isWritable() {
        return (mode & UnixModeBits.OWNER_WRITE) != 0;
    }

    public void setWritable(boolean writable) {
        mode |= UnixModeBits.OWNER_WRITE;
    }

    public boolean isExecutable() {
        return (mode & UnixModeBits.OWNER_EXECUTE) != 0;
    }

    public void setExecutable(boolean executable) {
        mode |= UnixModeBits.OWNER_EXECUTE;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isReadOnly() {
        return !isWritable();
    }

    public void setReadOnly(boolean readOnly) {
        setWritable(!readOnly);
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public UserPrincipal getOwner() {
        return owner;
    }

    public void setOwner(UserPrincipal owner) {
        this.owner = owner;
    }

    public GroupPrincipal getGroup() {
        return group;
    }

    public void setGroup(GroupPrincipal group) {
        this.group = group;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
