package net.bodz.swt.viz;

import net.bodz.bas.gui.style.StaticGUIStyleDeclaration;

public class StrictSwtVizStyleDeclaration
        extends StaticGUIStyleDeclaration
        implements ISwtControlStyleDeclaration {

    @Override
    public ISwtControlStyleDeclaration getParent() {
        return (ISwtControlStyleDeclaration) super.getParent();
    }

}
