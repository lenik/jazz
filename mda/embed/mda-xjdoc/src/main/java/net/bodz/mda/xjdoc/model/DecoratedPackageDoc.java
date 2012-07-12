package net.bodz.mda.xjdoc.model;

public abstract class DecoratedPackageDoc
        extends DecoratedElementDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedPackageDoc(IPackageDoc _orig) {
        super(_orig);
    }

    @Override
    public IPackageDoc getWrapped() {
        return (IPackageDoc) super.getWrapped();
    }

}
