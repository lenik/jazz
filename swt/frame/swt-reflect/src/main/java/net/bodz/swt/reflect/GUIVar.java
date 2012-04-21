package net.bodz.swt.reflect;

import net.bodz.bas.err.CheckException;
import net.bodz.bas.ui.Var;

public interface GUIVar<T>
        extends Var<T> {

    @Override
    GUIVarMeta getMeta();

    void check(Object newValue)
            throws CheckException;

}
