package net.bodz.swt.gui;

import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.ref.Var;

public interface GUIVar<T> extends Var<T> {

    @Override
    GUIVarMeta getMeta();

    void check(T newValue) throws CheckException;

}
