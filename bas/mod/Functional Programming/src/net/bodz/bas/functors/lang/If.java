package net.bodz.bas.functors.lang;

import net.bodz.bas.functors.ValidationException;
import net.bodz.bas.functors._Functor;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.EvalException;
import net.bodz.bas.lang.FunctorException;
import net.bodz.bas.nls.FPNLS;

public class If<T> extends _Functor<T> {

    protected _Functor<Boolean> predicate;
    protected _Functor<T>       truePart;
    protected _Functor<T>       falsePart;

    protected T                 evaluated;

    public If(_Functor<Boolean> predicate, _Functor<T> truePart, _Functor<T> falsePart) {
        this.predicate = predicate;
        this.truePart = truePart;
        this.falsePart = falsePart;
    }

    public If(_Functor<Boolean> predicate, _Functor<T> truePart) {
        this(predicate, truePart, null);
    }

    @Override
    public T eval2() throws EvalException, Control {
        if (isEvaluated())
            return evaluated;

        if (predicate.evalBoolean()) {
            return truePart.eval();
        } else {
            return falsePart.eval();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public _Functor<T> reduce() throws FunctorException {
        if (predicate != null)
            predicate = (_Functor<Boolean>) predicate.reduce();
        if (truePart != null)
            truePart = (_Functor<T>) truePart.reduce();
        if (falsePart != null)
            falsePart = (_Functor<T>) falsePart.reduce();
        if (predicate instanceof _Functor) {
            _Functor<Boolean> afPredicate = (_Functor<Boolean>) predicate;
            if (afPredicate.isEvaluated())
                try {
                    if (afPredicate.evalBoolean())
                        return truePart;
                    else
                        return falsePart;
                } catch (EvalException e) {
                    throw new FunctorException(e.getMessage(), e);
                } catch (Control c) {
                    throw new FunctorException(c.getMessage(), c);
                }
        }
        return this;
    }

    @Override
    public void validate() throws ValidationException {
        if (predicate == null) {
            throw new ValidationException(FPNLS.getString("If.nullPred")); //$NON-NLS-1$
        }
    }

}
