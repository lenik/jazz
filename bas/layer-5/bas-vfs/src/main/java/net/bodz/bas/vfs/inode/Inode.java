package net.bodz.bas.vfs.inode;

import java.io.Serializable;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.tree.AbstractMapTreeNode;

public class Inode
        extends AbstractMapTreeNode<Inode> {

    private static final long serialVersionUID = 1L;

    InodeDataType dataType;
    Serializable data;

    int modifiers;
    long creationTime;
    long lastModifiedTime;

    public Inode(Inode parent) {
        super(parent);
    }

    @Override
    protected Inode newChild()
            throws CreateException {
        return new Inode(this);
    }

    public InodeDataType getDataType() {
        return dataType;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
        if (data == null)
            dataType = null;
        else if (data instanceof byte[])
            dataType = InodeDataType.byteArray;
        else if (data instanceof String)
            dataType = InodeDataType.charArray;
        else
            throw new UnsupportedOperationException("data can only be byte[] or char[].");
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
