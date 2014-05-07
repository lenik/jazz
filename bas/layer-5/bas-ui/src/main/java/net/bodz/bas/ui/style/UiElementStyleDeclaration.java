package net.bodz.bas.ui.style;

import net.bodz.bas.ui.css3.ICss3StyleDeclaration;

public class UiElementStyleDeclaration
        extends MappedUiElementStyleDeclaration {

    private static final long serialVersionUID = 1L;

    private IUiElementStyleDeclaration parent;

    public UiElementStyleDeclaration(IUiElementStyleDeclaration parent) {
        setParent(parent);
    }

    @Override
    public IUiElementStyleDeclaration getParent() {
        return parent;
    }

    /**
     * @throws ClassCastException
     *             If the parent isn't a {@link IUiElementStyleDeclaration}.
     */
    @Override
    public void setParent(ICss3StyleDeclaration _parent) {
        IUiElementStyleDeclaration parent = (IUiElementStyleDeclaration) _parent;
        if (parent == null)
            parent = new MutableUiStyleDeclaration();
        this.parent = parent;
    }

    @Override
    public String getGroup() {
        // TODO
        return null;
    }

}
