package net.bodz.bas.functors.lang;

import net.bodz.bas.lang.EvalException;

public class Constant<T> extends TransientConstant<T> {

    public Constant(T value) {
        this.val = value;
        setFlagBits(EVALUATED);
    }

    @Override
    public T eval2() throws EvalException {
        return (T) val;
    }

    @Override
    public boolean isTransient() {
        return false;
    }

}
