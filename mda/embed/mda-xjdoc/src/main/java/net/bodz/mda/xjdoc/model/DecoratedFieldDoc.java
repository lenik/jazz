package net.bodz.mda.xjdoc.model;

public class DecoratedFieldDoc
        extends DecoratedElementDoc
        implements IFieldDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedFieldDoc(IFieldDoc _orig) {
        super(_orig);
    }

    @Override
    public IFieldDoc getWrapped() {
        return (IFieldDoc) super.getWrapped();
    }

    @Override
    public IClassDoc getClassDoc() {
        return getWrapped().getClassDoc();
    }

}
