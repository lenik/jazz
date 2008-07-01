package net.bodz.bas.functors.lang;

import net.bodz.bas.functors.IFunctor;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.EvalException;

public class CallFunction<T> extends _Functor<T> {

    public CallFunction(Function function) {

    }

    public CallFunction(Function function, IFunctor<?>... args) {

    }

    @Override
    public T eval2() throws EvalException {
        return null;
    }

}
