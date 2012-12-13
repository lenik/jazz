package net.bodz.bas.t.ref;

import net.bodz.bas.err.ReadOnlyException;

public class ConstVar<T>
        extends Var<T> {

    private static final long serialVersionUID = 1L;

    public ConstVar(Class<?> valueType, T init) {
        super(valueType, init);
    }

    @Override
    public void set(T value) {
        throw new ReadOnlyException("Can't mutate a const ref");
    }

    @Override
    public String toString() {
        return "Constant: " + get();
    }

}
