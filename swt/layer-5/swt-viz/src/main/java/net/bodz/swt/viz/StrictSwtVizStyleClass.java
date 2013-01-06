package net.bodz.swt.viz;

import net.bodz.bas.gui.style.StaticGUIStyleClass;

public class StrictSwtVizStyleClass
        extends StaticGUIStyleClass
        implements ISwtControlStyleClass {

    @Override
    public ISwtControlStyleClass getParent() {
        return (ISwtControlStyleClass) super.getParent();
    }

}
