package net.bodz.bas.lang.ref;

import java.io.Serializable;

public abstract class AbstractRef<T>
        implements Ref<T>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void remove() {
        set(null);
    }

}
