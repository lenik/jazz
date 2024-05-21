package net.bodz.bas.ctx.sys;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t.ref.TypedRef;

public abstract class SystemConstantRef<T>
        implements
            TypedRef<T> {

    @Override
    public void set(T value) {
        throw new ReadOnlyException();
    }

    @Override
    public void remove() {
        set(null);
    }

}
