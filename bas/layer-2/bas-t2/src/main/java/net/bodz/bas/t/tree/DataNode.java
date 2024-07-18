package net.bodz.bas.t.tree;

import net.bodz.bas.c.object.Nullables;

public class DataNode<T>
        extends MapTreeNode<DataNode<T>> {

    private static final long serialVersionUID = 1L;

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Nullables.toString(data);
    }

}
