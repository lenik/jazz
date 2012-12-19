package net.bodz.bas.vfs.util;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.tree.AbstractMapTreeNode;

public class Inode
        extends AbstractMapTreeNode<Inode> {

    private static final long serialVersionUID = 1L;

    private Object data;

    // private short uid;
    // private short gid;
    // private int permission;
    private boolean readable = true;
    private boolean writable = true;
    private boolean executable = true;
    private boolean hidden = false;

    private long creationTime = System.currentTimeMillis();
    private long lastModifiedTime = creationTime;

    public Inode(Inode parent) {
        super(parent);
    }

    @Override
    protected Inode newChild()
            throws CreateException {
        return new Inode(this);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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

}
