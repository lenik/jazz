package net.bodz.bas.collection.preorder;

public class SriTypeMap<V>
        extends PreorderMap<Class<?>, V> {

    private static final long serialVersionUID = 1L;

    public SriTypeMap() {
        super(SriTypePreorder.getInstance());
    }

}
