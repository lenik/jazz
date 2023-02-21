package net.bodz.swt.viz;

import net.bodz.bas.ui.style.NullUiElementStyleDeclaration;

public class NullSwtControlStyleDeclaration
        extends NullUiElementStyleDeclaration
        implements
            ISwtControlStyleDeclaration {

    @Override
    public ISwtControlStyleDeclaration getParent() {
        return null;
    }

    public static final NullSwtControlStyleDeclaration INSTANCE = new NullSwtControlStyleDeclaration();

}
