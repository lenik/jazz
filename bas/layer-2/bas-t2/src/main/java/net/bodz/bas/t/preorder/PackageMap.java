package net.bodz.bas.t.preorder;

public class PackageMap<T>
        extends PreorderTreeMap<String, T> {

    private static final long serialVersionUID = 1L;

    public PackageMap() {
        super(PackageNamePreorder.getInstance());
    }

}
