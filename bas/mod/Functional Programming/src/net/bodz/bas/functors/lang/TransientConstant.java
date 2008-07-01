package net.bodz.bas.functors.lang;

import net.bodz.bas.functors._Functor;

public abstract class TransientConstant<T> extends _Functor<T> {

    @Override
    public boolean isReduced() {
        return true;
    }

    @Override
    public boolean isValidated() {
        return true;
    }

    @Override
    public boolean isOutgo() {
        return false;
    }

    @Override
    public boolean isEvaluated() {
        return true;
    }

    @Override
    public boolean isTransient() {
        return true;
    }

    @Override
    public boolean isClosed() {
        return true;
    }

}
