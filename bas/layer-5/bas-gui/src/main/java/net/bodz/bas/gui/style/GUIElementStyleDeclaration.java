package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.ICss3StyleDeclaration;

public class GUIElementStyleDeclaration
        extends MappedGUIElementStyleDeclaration {

    private static final long serialVersionUID = 1L;

    private IGUIElementStyleDeclaration parent;

    public GUIElementStyleDeclaration(IGUIElementStyleDeclaration parent) {
        setParent(parent);
    }

    @Override
    public IGUIElementStyleDeclaration getParent() {
        return parent;
    }

    /**
     * @throws ClassCastException
     *             If the parent isn't a {@link IGUIElementStyleDeclaration}.
     */
    @Override
    public void setParent(ICss3StyleDeclaration _parent) {
        IGUIElementStyleDeclaration parent = (IGUIElementStyleDeclaration) _parent;
        if (parent == null)
            parent = new MutableGUIStyleDeclaration();
        this.parent = parent;
    }

    @Override
    public String getGroup() {
        // TODO
        return null;
    }

}
