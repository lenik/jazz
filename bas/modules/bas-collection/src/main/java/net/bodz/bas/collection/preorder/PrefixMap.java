package net.bodz.bas.collection.preorder;

public class PrefixMap<V>
        extends PreorderMap<String, V> {

    private static final long serialVersionUID = 1L;

    public PrefixMap() {
        super(StartswithPreorder.getInstance());
    }

}
