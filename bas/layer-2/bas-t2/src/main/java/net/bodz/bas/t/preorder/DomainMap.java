package net.bodz.bas.t.preorder;

public class DomainMap<T>
        extends PreorderTreeMap<String, T> {

    private static final long serialVersionUID = 1L;

    public DomainMap() {
        super(DomainNamePreorder.getInstance());
    }

}
