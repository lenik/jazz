package net.bodz.bas.t.ref;

import net.bodz.bas.err.ReadOnlyException;

public abstract class ReadOnlyRef<T>
        implements Ref<T> {

    @Override
    public void set(T value) {
        throw new ReadOnlyException();
    }

    @Override
    public void remove() {
        throw new ReadOnlyException();
    }

}
