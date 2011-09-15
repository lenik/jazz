package net.bodz.bas.collection.preorder;

public class SriTypeSet
        extends PreorderSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    public SriTypeSet() {
        super(SriTypePreorder.getInstance());
    }

}
