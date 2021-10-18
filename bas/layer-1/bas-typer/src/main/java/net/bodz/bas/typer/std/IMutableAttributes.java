package net.bodz.bas.typer.std;

import net.bodz.bas.rtx.IAttributed;

public interface IMutableAttributes
        extends
            IAttributes,
            IAttributed {

    @Override
    default <T> T getAttribute(String name) {
        return getAttribute(name, null);
    }

    void removeAttribute(String name);

    void setAttributeTypers(String name, ITyperFamily<?> typers);

}
