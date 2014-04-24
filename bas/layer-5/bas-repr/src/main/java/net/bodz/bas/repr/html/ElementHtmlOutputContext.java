package net.bodz.bas.repr.html;

public abstract class ElementHtmlOutputContext
        extends AbstractHtmlOutputContext {

    private IHttpReprContext parent;

    public ElementHtmlOutputContext(IHttpReprContext parent) {
        if (parent == null)
            throw new NullPointerException("parent");
        this.parent = parent;
    }

    @Override
    public IHttpReprContext getParent() {
        return parent;
    }

}
