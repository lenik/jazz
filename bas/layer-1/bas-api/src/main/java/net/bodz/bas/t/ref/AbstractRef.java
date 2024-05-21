package net.bodz.bas.t.ref;

import java.io.Serializable;

public abstract class AbstractRef<T>
        implements
            TypedRef<T>,
            Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void remove() {
        set(null);
    }

}
