package net.bodz.bas.ui.dom1;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.ui.style.IUiElementStyleDeclaration;

public interface IUiRef<T>
        extends IRefEntry<T>, IUiElement {

    @Override
    IUiElementStyleDeclaration getStyle();

}
