package net.bodz.bas.c.java.nio;

import net.bodz.bas.t.preorder.PreorderTreeMap;

public class SubPathMap<T>
        extends PreorderTreeMap<String, T> {

    private static final long serialVersionUID = 1L;

    public SubPathMap() {
        super(SubPathPreorder.INSTANCE);
    }

}
