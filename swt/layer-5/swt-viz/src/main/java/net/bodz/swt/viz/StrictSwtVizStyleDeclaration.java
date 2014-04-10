package net.bodz.swt.viz;

import net.bodz.bas.gui.style.MutableGUIStyleDeclaration;

public class StrictSwtVizStyleDeclaration
        extends MutableGUIStyleDeclaration
        implements ISwtControlStyleDeclaration {

    private static final long serialVersionUID = 1L;

    @Override
    public ISwtControlStyleDeclaration getParent() {
        return (ISwtControlStyleDeclaration) super.getParent();
    }

}
