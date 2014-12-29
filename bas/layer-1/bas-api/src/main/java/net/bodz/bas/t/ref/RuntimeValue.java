package net.bodz.bas.t.ref;

import net.bodz.bas.err.ReadOnlyException;

public abstract class RuntimeValue<T>
        extends AbstractRef<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public void set(T value) {
        throw new ReadOnlyException();
    }

}
