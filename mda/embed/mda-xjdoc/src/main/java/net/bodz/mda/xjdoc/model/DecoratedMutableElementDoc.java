package net.bodz.mda.xjdoc.model;

public class DecoratedMutableElementDoc
        extends _DecoratedElementDoc<IMutableElementDoc>
        implements
            IMutableElementDoc {

    private static final long serialVersionUID = 1L;

    public DecoratedMutableElementDoc(IMutableElementDoc _orig) {
        super(_orig);
    }

    @Override
    public <T extends IDocTag<?>> T makeTag(String name) {
        return getWrapped().makeTag(name);
    }

    @Deprecated
    @Override
    public void setTag(String tagName, IDocTag<?> tag) {
        getWrapped().setTag(tagName, tag);
    }

    @Override
    public IDocTag<?> removeTag(String tagName) {
        return getWrapped().removeTag(tagName);
    }

}
