package net.bodz.bas.t.preorder;

public class PrefixMap<V>
        extends PreorderTreeMap<String, V> {

    private static final long serialVersionUID = 1L;

    public PrefixMap() {
        super(StartswithPreorder.getInstance());
    }

}
