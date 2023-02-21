package net.bodz.swt.viz;

import net.bodz.bas.ui.dom1.DecoratedUiRef;
import net.bodz.bas.ui.dom1.IUiRef;

public class DefaultSwtUiRef<T>
        extends DecoratedUiRef<T>
        implements
            ISwtUiRef<T> {

    private static final long serialVersionUID = 1L;

    public DefaultSwtUiRef(IUiRef<T> _orig) {
        super(_orig);
    }

    @Override
    public ISwtControlStyleDeclaration getStyle() {
        return NullSwtControlStyleDeclaration.INSTANCE;
    }

}
