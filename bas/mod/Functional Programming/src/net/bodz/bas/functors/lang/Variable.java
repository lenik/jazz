package net.bodz.bas.functors.lang;

import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.EvalException;

public class Variable<T> extends _Functor<T> {

    public Variable(String variableName) {
    }

    @Override
    public T eval2() throws EvalException {
        return null;
    }

}
