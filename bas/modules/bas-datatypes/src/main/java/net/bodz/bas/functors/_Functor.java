package net.bodz.bas.functors;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang2.EvalException;

public abstract class _Functor<T> extends Functor2<T> {

    private int flags = 0;
    protected T val;

    @Override
    public T eval() throws EvalException, Control {
        if (isTransient() || !isEvaluated()) {
            val = eval2();
            setFlagBits(EVALUATED);
        }
        return val;
    }

    protected abstract T eval2() throws EvalException, Control;

    @Override
    protected final int getFlags() {
        return flags;
    }

    @Override
    protected final void setFlags(int flags) {
        this.flags = flags;
    }

    @Override
    protected final void setFlagBits(int bits) {
        flags |= bits;
    }

    @Override
    protected final void setFlagBits(int bits, int mask) {
        flags &= ~mask;
        flags |= bits;
    }

    @Override
    protected final void clearFlagBits(int bits) {
        flags &= ~bits;
    }

    @Override
    public boolean isReduced() {
        return (flags & REDUCED) != 0;
    }

    @Override
    public boolean isValidated() {
        return (flags & VALIDATED) != 0;
    }

    @Override
    public boolean isEvaluated() {
        return (flags & EVALUATED) != 0;
    }

    @Override
    public boolean isTransient() {
        return (flags & TRANSIENT) != 0;
    }

    @Override
    public boolean isOutgo() {
        return (flags & OUTGO) != 0;
    }

    @Override
    public boolean isClosed() {
        return (flags & CLOSED) != 0;
    }

}
