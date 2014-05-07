package net.bodz.swt.viz;

import net.bodz.bas.ui.style.MutableUiStyleDeclaration;

public class StrictSwtVizStyleDeclaration
        extends MutableUiStyleDeclaration
        implements ISwtControlStyleDeclaration {

    private static final long serialVersionUID = 1L;

    @Override
    public ISwtControlStyleDeclaration getParent() {
        return (ISwtControlStyleDeclaration) super.getParent();
    }

}
