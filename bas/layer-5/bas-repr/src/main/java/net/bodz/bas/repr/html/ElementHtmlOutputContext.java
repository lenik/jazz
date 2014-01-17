package net.bodz.bas.repr.html;

public abstract class ElementHtmlOutputContext
        extends AbstractHtmlOutputContext {

    private IHtmlOutputContext parent;

    public ElementHtmlOutputContext(IHtmlOutputContext parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    @Override
    public IHtmlOutputContext getParent() {
        return parent;
    }

}
