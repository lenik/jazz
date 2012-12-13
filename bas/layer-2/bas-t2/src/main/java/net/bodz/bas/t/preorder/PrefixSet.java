package net.bodz.bas.t.preorder;

public class PrefixSet
        extends PreorderSet<String> {

    private static final long serialVersionUID = 1L;

    public PrefixSet() {
        super(StartswithPreorder.getInstance());
    }

}
