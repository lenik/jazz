package net.bodz.bas.ui.css3;

public class Css3StyleDeclaration
        extends MappedCss3StyleDeclaration {

    private static final long serialVersionUID = 1L;

    private ICss3StyleDeclaration parent;

    public Css3StyleDeclaration(ICss3StyleDeclaration parent) {
        setParent(parent);
    }

    @Override
    public ICss3StyleDeclaration getParent() {
        return parent;
    }

    @Override
    public void setParent(ICss3StyleDeclaration parent) {
        if (parent == null)
            parent = new MutableCss3StyleDeclaration();
        this.parent = parent;
    }

}
