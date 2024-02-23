package net.bodz.bas.t.variant;

import net.bodz.bas.rtx.IAttributed;

/**
 * @see IAttributed
 */
public interface IVarAttributes {

    IVariant getAttribute(String name);

    void setAttribute(String name, Object value);

}
