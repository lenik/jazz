package net.bodz.bas.gui.dom1;

import net.bodz.bas.gui.style.IGUIElementStyleDeclaration;
import net.bodz.bas.potato.ref.IRefEntry;

public interface IGUIRefEntry<T>
        extends IRefEntry<T>, IGUIElement {

    IGUIElementStyleDeclaration getStyle();

}
