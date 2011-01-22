package net.bodz.swt.reflect;

import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.ref.Var;

public interface GUIVar<T> extends Var<T> {

    @Override
    GUIVarMeta getMeta();

    void check(Object newValue) throws CheckException;

}
