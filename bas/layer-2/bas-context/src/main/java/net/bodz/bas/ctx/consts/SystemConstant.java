package net.bodz.bas.ctx.consts;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t.ref.Ref;

public abstract class SystemConstant<T>
        implements Ref<T> {

    @Override
    public void set(T value) {
        throw new ReadOnlyException();
    }

    @Override
    public void remove() {
        set(null);
    }

}
