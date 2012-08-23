package net.bodz.bas.potato.ref;

import net.bodz.bas.err.ReadOnlyException;

public class ConstVariable<T>
        extends Variable<T> {

    private static final long serialVersionUID = 1L;

    public ConstVariable(String name, T value) {
        super(name, value);
    }

    @Override
    public void set(T value) {
        throw new ReadOnlyException("Can't mutate a const ref");
    }

}
