package net.bodz.swt.viz;

import net.bodz.bas.gui.css3.ICss3StyleDeclaration;
import net.bodz.bas.gui.style.MappedGUIElementStyleDeclaration;

public abstract class MappedSwtVizStyleDeclaration
        extends MappedGUIElementStyleDeclaration
        implements ISwtControlStyleDeclaration /* , IDisposable */{

    private static final long serialVersionUID = 1L;

    @Override
    public ISwtControlStyleDeclaration getParent() {
        return (ISwtControlStyleDeclaration) super.getParent();
    }

    @Override
    public void setParent(ICss3StyleDeclaration parent) {
        if (parent != null)
            if (!(parent instanceof ISwtControlStyleDeclaration))
                throw new IllegalArgumentException("Invalid parent: " + parent);
        super.setParent(parent);
    }

}
