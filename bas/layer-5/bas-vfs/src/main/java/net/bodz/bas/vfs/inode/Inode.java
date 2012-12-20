package net.bodz.bas.vfs.inode;

import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;

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
    private boolean readable = true;
    private boolean writable = true;
    private boolean executable = true;

    // for DOS.
    private boolean archive;
    private boolean readOnly;
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
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public boolean isExecutable() {
        return executable;
    }

    public void setExecutable(boolean executable) {
        this.executable = executable;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
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
