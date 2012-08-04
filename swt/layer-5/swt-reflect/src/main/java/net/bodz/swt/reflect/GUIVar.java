package net.bodz.swt.reflect;

import net.bodz.bas.traits.ValidateException;
import net.bodz.bas.ui.Var;

public interface GUIVar<T>
        extends Var<T> {

    GUIVarMeta getMeta();

    void validate(Object newValue)
            throws ValidateException;

}
