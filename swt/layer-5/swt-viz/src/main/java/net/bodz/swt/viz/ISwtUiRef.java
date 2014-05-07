package net.bodz.swt.viz;

import net.bodz.bas.ui.dom1.IUiRef;

public interface ISwtUiRef<T>
        extends IUiRef<T> {

    @Override
    ISwtControlStyleDeclaration getStyle();

}
