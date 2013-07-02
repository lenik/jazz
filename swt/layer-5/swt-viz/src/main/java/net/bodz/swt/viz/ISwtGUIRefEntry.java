package net.bodz.swt.viz;

import net.bodz.bas.gui.dom1.IGUIRefEntry;

public interface ISwtGUIRefEntry<T>
        extends IGUIRefEntry<T> {

    @Override
    ISwtControlStyleClass getStyle();

}
