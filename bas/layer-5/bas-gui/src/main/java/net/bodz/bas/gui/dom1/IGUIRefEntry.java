package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleClass;
import net.bodz.bas.potato.ref.IRefEntry;

public interface IGUIRefEntry<T>
        extends IRefEntry<T>, IGUIElement {

    IGUIElementStyleClass getStyle();

}
